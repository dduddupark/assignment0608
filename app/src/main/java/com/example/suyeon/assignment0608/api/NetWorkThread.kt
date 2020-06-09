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


/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */

enum class HttpMethod {
    GET, POST, PUT
}

open class NetWorkThread(
    private val requestType: HttpMethod,
    private val endUrl: String,
    private val params: Map<String, String>?,
    private val networkFinishListener: NetworkFinishListener
) : AsyncTask<Void, Void, String?>() {

    val TAG = "NetWorkThread"

    companion object {
        const val MAIN_URL = "users"
        const val DETAIL_URL = "users"
        const val CREATE_URL = "users"
        const val UPDATE_URL = "update"
    }

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

            var url = BuildConfig.BASE_URL + endUrl

            if (HttpMethod.GET == requestType) {
                params?.let {
                    url += getParams(params)
                }
            }

            //Log.d(TAG, url)

            val httpConnection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection

            httpConnection.requestMethod = requestType.toString()
            httpConnection.connectTimeout = 3000
            httpConnection.addRequestProperty("Content-Type", "application/json")

            Log.d(TAG, "httpConnection.url = " + httpConnection.url)
            Log.d(TAG, "httpConnection.requestMethod = " + httpConnection.requestMethod)

            if (HttpMethod.POST == requestType) {

                val os = httpConnection.outputStream // 서버로 보내기 위한 출력 스트림
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8")) // UTF-8로 전송
                val data = getPostJson(params)
                Log.d(TAG, "data = " + data.toString())
                bw.write(data.toString()) // 매개변수 전송
                bw.flush()
                bw.close()
                os.close()
            }

            Log.d(TAG, "httpConnection.responseCode = " + httpConnection.responseCode)

            if (httpConnection.responseCode == HttpURLConnection.HTTP_OK) {

                var readData = ""
                val reader =
                    BufferedReader(InputStreamReader(httpConnection.inputStream)) // 서버의 응답을 읽기 위한 입력 스트림

                while (true) {
                    val line = reader.readLine() ?: break
                    readData = readData.plus(line)
                }

                response = readData
            } else {

                Log.d(TAG, "error = " + httpConnection.errorStream.toString())

                response = httpConnection.responseCode.toString().plus(" ")
                    .plus(httpConnection.responseMessage)
            }

            httpConnection.disconnect()

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