package com.example.suyeon.assignment0608.api

import android.util.Log
import com.example.suyeon.assignment0608.BuildConfig
import com.example.suyeon.assignment0608.data.Result
import com.example.suyeon.assignment0608.data.ResultCode
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

private const val CONNECTION_TIMEOUT = 5000
private const val READ_TIMEOUT = 5000
private const val TAG = "NetWorkThread"

enum class HttpMethod {
    GET, POST, PUT, DELETE
}

fun netWorkThread(
    requestType: HttpMethod,
    urlParams: Map<String, String>?,
    bodyParams: Map<String, String>?
): Result {

    lateinit var result: Result

    //error시 객체해제
    var bufferedReader: BufferedReader? = null
    var bufferWriter: BufferedWriter? = null

    try {
        var url = BuildConfig.BASE_URL

        if (HttpMethod.GET == requestType || HttpMethod.PUT == requestType || HttpMethod.DELETE == requestType) {
            urlParams?.let {
                url += getParams(urlParams)
            }
        }

        val httpsConnection = URL(url).openConnection() as HttpsURLConnection

        httpsConnection.let {
            it.requestMethod = requestType.toString()
            //서버 연결 시간
            it.connectTimeout = CONNECTION_TIMEOUT
            //데이터 읽는 시간
            it.readTimeout = READ_TIMEOUT
            //header
            it.addRequestProperty("Content-Type", "application/json")
        }

        Log.d(TAG, "httpConnection.url = " + httpsConnection.url)
        Log.d(TAG, "httpConnection.requestMethod = " + httpsConnection.requestMethod)

        if (HttpMethod.POST == requestType || HttpMethod.PUT == requestType) {
            val os = httpsConnection.outputStream // 서버로 보내기 위한 출력 스트림
            bufferWriter = BufferedWriter(OutputStreamWriter(os, "UTF-8")) /* UTF-8로 전송 */
            bufferWriter.let {
                it.write(getPostJson(bodyParams).toString()) // 매개변수 전송
                it.flush()
                it.close()
            }

            os.close()
        }

        Log.d(TAG, "httpConnection.responseCode = " + httpsConnection.responseCode)

        if (httpsConnection.responseCode == HttpURLConnection.HTTP_OK ||
            httpsConnection.responseCode == HttpURLConnection.HTTP_CREATED ||
            httpsConnection.responseCode == HttpURLConnection.HTTP_NO_CONTENT
        ) {
            //string -> stringBuffer -> StringBuilder
            //StringBuffer는 각 메서드별로 Synchronized Keyword가 존재하여, 멀티스레드 환경에서도 동기화를 지원.
            //반면, StringBuilder는 동기화를 보장하지 않음. 속도가 더 빠름

            bufferedReader =
                BufferedReader(InputStreamReader(httpsConnection.inputStream)) // 서버의 응답을 읽기 위한 입력 스트림

            val data = buildString {
                while (true) {
                    append(bufferedReader.readLine() ?: break)
                }
            }

            Log.d(TAG, "httpConnection.data = " + data)

            result = Result(ResultCode.SUCCESS, data)

        } else {
            Log.d(TAG, "error = " + httpsConnection.errorStream.toString())

            result = Result(
                ResultCode.ERROR, httpsConnection.responseCode.toString().plus(" ")
                    .plus(httpsConnection.responseMessage)
            )
        }

        httpsConnection.disconnect()

    } catch (e: Exception) {

        Log.d(TAG, "Exception = " + e.toString())

        result = Result(ResultCode.ERROR, e.toString())

    } finally {
        try {
            bufferedReader?.close()
            bufferWriter?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    return result
}

private fun getParams(params: Map<String, String>?): String {
    //REST api 규격 형식
    return if (params != null) {
        buildString {
            for (key in params) {
                append("/")
                append(key.value)
            }
        }
    } else {
        ""
    }
}

private fun getPostJson(params: Map<String, String>?): JSONObject {
    return JSONObject().apply {
        if (!params.isNullOrEmpty()) {
            for ((key, value) in params) {
                put(key, value)
            }
        }
    }
}

