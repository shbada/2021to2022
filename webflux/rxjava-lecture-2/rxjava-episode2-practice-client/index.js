$(document).ready(function(){
    let eventSource = null;

    $("#btnStart").click(function(){

        getWeatherData();
        function getWeatherData() {
            eventSource = new EventSource("http://localhost:8080/stream/weather");

            // 통지된 데이터가 표시되는 영역 초기화
            eventSource.onopen = ev => {
                $("#data-area").empty();
                $("#btnStart").attr("disabled", true);
                $("#btnStop").attr("disabled", false);
            }

            // 서버측에서 메시지를 전달하면 실행
            eventSource.onmessage = e => {
                let result = JSON.parse(e.data); // 전달받은 데이터
                console.log(result);
                // 데이터를 표시
                $("#data-area").append("<div>습도: " + result.humidity + ", 온도: " + result.temperature + "</div>");
            }

            // 서버 측에서 에러가 발생할 경우 실행
            eventSource.onerror = error => {
                if (error) {
                    alert("연결이 되지 않습니다. 잠시 뒤 다시 시도해주세요");
                    eventSource.close();
                }
            }
        }
    });

    $("#btnStop").click(function () {
        eventSource.close();
        $("#btnStart").attr("disabled", false);
        $("#btnStop").attr("disabled", true);
    });
})
