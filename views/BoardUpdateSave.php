<?php
require_once "../controller/Board.php";

$board = new Board();
$board->updateBoard($_GET[title], $_GET[content], $_GET[idx]);
?>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div id="read">
    <form action="BoardList.php">
        <input type="submit" value="목록가기">
    </form>
</div>
</body>
</html>
