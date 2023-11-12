<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>班次信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 20px;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: auto;
        }

        h2 {
            color: #333;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
        }

        .schedule {
            margin-top: 20px;
        }

        .time {
            font-size: 18px;
            margin-bottom: 10px;
        }

        .icon {
            font-size: 24px;
            margin-right: 5px;
        }

        #current-time {
            font-size: 20px;
            color: #555;
            margin-top: 15px;
        }
        /*#file-input {*/
        /*    display: none;*/
        /*}*/

        #upload-btn {
            background-color: #4caf50;
            color: #fff;
            padding: 10px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <div id="current-time"></div>
    <h2>下次班次信息</h2>
    <div class="schedule">
        <div class="time"><span class="icon" >&#128197;</span> 下次白班时间: ${map.dayTime}（${map.dayWeek}）</div>
        <div class="time"><span class="icon">&#128198;</span> 下次晚班时间: ${map.nightTime}（${map.nightWeek}）</div>
        <div class="time"><span class="icon">&#127748;</span> 下次白加晚时间:${map.allDayTime}（${map.allDayWeek}）</div>
        <div class="time"><span class="icon">&#127774;</span> 下次休息时间: ${map.restTime}（${map.restWeek}）</div>
        <div class="time"> <span style="color: red;font-size: 12px" >提示：如果排班有变动，请及时在下方重新上传排班表！</span></div>
        <form action="upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" id="file" required accept=".xlsx,.xls"/>
            <button type="submit" id="upload-btn" >上传排班Excel表 </button>
        </form>
    </div>
</div>

<script>
    function updateCurrentTime() {
        var currentTimeElement = document.getElementById('current-time');
        var currentTime = new Date();
        var hours = currentTime.getHours();
        var minutes = currentTime.getMinutes();
        var seconds = currentTime.getSeconds();

        var formattedTime = (hours < 10 ? '0' : '') + hours + ':' +
            (minutes < 10 ? '0' : '') + minutes + ':' +
            (seconds < 10 ? '0' : '') + seconds;

        currentTimeElement.textContent = '当前时间: ' + formattedTime;
    }

    // 更新时间每秒钟
    setInterval(updateCurrentTime, 1000);

    // 初始化页面加载时更新一次
    updateCurrentTime();
</script>
</body>
</html>
