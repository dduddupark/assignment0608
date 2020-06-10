package com.example.suyeon.assignment0608.api

import android.os.AsyncTask
import android.util.Log
import com.example.suyeon.assignment0608.BuildConfig
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
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

enum class HttpMethod {
    GET, POST, PUT, DELETE
}

open class NetWorkThread(
    private val requestType: HttpMethod,
    private val urlParams: Map<String, String>? = null,
    private val bodyParams: Map<String, String>? = null,
    private val networkFinishListener: NetworkFinishListener
) : AsyncTask<Void, Void, String?>() {

    private val TIME_OUT = 3000

    private val TAG = "NetWorkThread"

    interface NetworkFinishListener {
        fun onFinished(result: String)
    }

    override fun doInBackground(vararg params: Void?): String? {
        return send()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        result?.let {
            networkFinishListener.onFinished(result)
        }
    }

    private fun send(): String? {

        var response: String?

        try {
            var url = BuildConfig.BASE_URL

            if (HttpMethod.GET == requestType || HttpMethod.PUT == requestType || HttpMethod.DELETE == requestType) {
                urlParams?.let {
                    url += getParams(urlParams)
                }
            }

            val httpsConnection = URL(url).openConnection() as HttpsURLConnection

            httpsConnection.requestMethod = requestType.toString()
            httpsConnection.connectTimeout = TIME_OUT
            httpsConnection.addRequestProperty("Content-Type", "application/json")

            Log.d(TAG, "httpConnection.url = " + httpsConnection.url)
            Log.d(TAG, "httpConnection.requestMethod = " + httpsConnection.requestMethod)

            if (HttpMethod.POST == requestType || HttpMethod.PUT == requestType) {
                val os = httpsConnection.outputStream // 서버로 보내기 위한 출력 스트림
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8")) // UTF-8로 전송
                val data = getPostJson(bodyParams)
                Log.d(TAG, "data = " + data.toString())
                bw.write(data.toString()) // 매개변수 전송
                bw.flush()
                bw.close()
                os.close()
            }

            Log.d(TAG, "httpConnection.responseCode = " + httpsConnection.responseCode)

            if (httpsConnection.responseCode == HttpURLConnection.HTTP_OK ||
                httpsConnection.responseCode == HttpURLConnection.HTTP_CREATED ||
                httpsConnection.responseCode == HttpURLConnection.HTTP_NO_CONTENT
            ) {
                var readData = ""
                val reader =
                    BufferedReader(InputStreamReader(httpsConnection.inputStream)) // 서버의 응답을 읽기 위한 입력 스트림

                while (true) {
                    val line = reader.readLine() ?: break
                    readData = readData.plus(line)
                }

                response = readData

            } else {
                Log.d(TAG, "error = " + httpsConnection.errorStream.toString())

                response = httpsConnection.responseCode.toString().plus(" ")
                    .plus(httpsConnection.responseMessage)
            }

            httpsConnection.disconnect()

        } catch (e: Exception) {

            Log.d(TAG, "Exception")

            response = e.toString()
        }

        return response
    }

    private fun getParams(params: Map<String, String>?): String {
        return if (params != null) {
            val sb = StringBuffer()
            for (key in params) {
                sb.append("/")
                sb.append(key.value)
            }
            sb.toString()
        } else {
            ""
        }
    }

    private fun getPostJson(params: Map<String, String>?): JSONObject {
        return JSONObject().apply {
            if (!params.isNullOrEmpty()) {
                for (entry in params.entries) {
                    put(entry.key, entry.value)
                }
            }
        }
    }

}