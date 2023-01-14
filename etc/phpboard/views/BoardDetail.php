<?php
require_once "../controller/Board.php";

$idx = $_GET[idx];

$board = new Board();
$result = $board->selectBoardInfo($idx);
?>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>게시글 상세</title>
</head>
<body>
<div id="read">
    <form action="BoardUpdateSave.php" methoid="GET">
        <input type="hidden" name="idx" value="<?= $idx ?>">
        <p>제목
            <textarea name="title" id="title" rows="1" cols="55" placeholder="제목" maxlength="100" required><?= $result["title"] ?></textarea>
        </p>
        <p>작성자 : <?= $result["userName"] ?></p>
        <p>작성일 : <?= $result["createdDate"] ?></p>
        <p>내용</p>
        <textarea name="content" id="content" placeholder="내용" required><?= $result["content"] ?></textarea>
        <input type="submit" value="수정">
    </form>
    <form action="BoardList.php">
        <input type="submit" value="목록가기">
    </form>
</div>
</body>
</html>
