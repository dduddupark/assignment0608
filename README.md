# assignment0608
안드로이드에서 제공하는 기능만으로 서버와 통신하는 화면 만들기

통신부분 : HttpsURLConnection, JSON, Coroutine

화면부분 : MVP 구조


0608
- AsyncTask 사용
- MVC 구조

0617
- AsyncTask → Coroutine 으로 변경
- 통신부분 repository 구조로 수정
- MVC → MVP 구조로 변경

0625
- json parsing 분리
- 서버데이터를 받는 Generic Class 추가(Response.class)
- 서버 error처리, view progressbar 추가

0629
- Response.class 쪽 Error.class 수정
- BaseInterface 추가하여 error, loadingbar 공통으로 뺌



[사용한 kotlin 기능] 

참고서적 : Kotlin in Action

- p.115 확장함수 (toast message, fragmentManager)
- p.241 apply 함수 (network read buffer)
- p.383 제네릭스 (Response.class)
