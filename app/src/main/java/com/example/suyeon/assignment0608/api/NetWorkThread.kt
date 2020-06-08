package com.example.suyeon.assignment0608.api

import android.os.AsyncTask
import android.util.Log
import com.example.suyeon.assignment0608.BuildConfig
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
open class NetWorkThread(
    private val requestType: String,
    private val url: String,
    private val params: Map<String, String>?,
    private val networkFinishListener: NetworkFinishListener
) : AsyncTask<Void, Void, String?>() {

    val TAG = "NetWorkThread"

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

        var response: String? = null

        try {

            val url = URL(BuildConfig.BASE_URL + url)

            Log.d(TAG, url.toString())

            val httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

            httpConnection.requestMethod = requestType
            httpConnection.connectTimeout = 3000


            if (!params.isNullOrEmpty()) {
                val os = httpConnection.outputStream // 서버로 보내기 위한 출력 스트림
                val bw = BufferedWriter(OutputStreamWriter(os, "UTF-8")) // UTF-8로 전송
                val data = getPostString(params)
                if (data != null) {
                    bw.write(data) // 매개변수 전송
                }
                bw.flush()
                bw.close()
                os.close()
            }

            if (httpConnection.responseCode == HttpURLConnection.HTTP_OK) {

                var readData = ""
                val reader =
                    BufferedReader(InputStreamReader(httpConnection.inputStream)) // 서버의 응답을 읽기 위한 입력 스트림

                while (true) {
                    val line = reader.readLine() ?: break
                    readData = readData.plus(line)
                }

                response = readData
            }


        } catch (e: Exception) {

            response = e.toString()
        }

        return response

    }

    private fun getPostString(params: Map<String, String>?): String? {

        val result = StringBuilder()

        if (!params.isNullOrEmpty()) {
            var first = true // 첫 번째 매개변수 여부

            for (entry in params.entries) {
                if (first)
                    first = false
                else
                // 첫 번째 매개변수가 아닌 경우엔 앞에 &를 붙임
                    result.append("&")

                try { // UTF-8로 주소에 키와 값을 붙임
                    result.append(URLEncoder.encode(entry.key, "UTF-8"))
                    result.append("=")
                    result.append(URLEncoder.encode(entry.value, "UTF-8"))
                } catch (ue: UnsupportedEncodingException) {
                    ue.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

        return result.toString()
    }

}