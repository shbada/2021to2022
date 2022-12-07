<?php
require_once "../controller/Board.php";


$board = new Board();
$result = $board->selectBoardList();
?>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>게시판 리스트</title>
</head>
<body>
<div>
    <table>
        <tr id="info">
            <td class="num">번호</td>
            <td class="title">제목</td>
            <td class="writer">내용</td>
            <td class="writer">조회수</td>
            <td class="writer">작성자</td>
            <td class="date">작성날짜</td>
        </tr>
        <?php foreach($result as $row) : ?>
            <tr>
                <td class="idx"><?= $row[idx] ?></td>
                <td class="title"><a href="/views/BoardDetail.php?idx=<?=$row["idx"]?>"><?= $row[title] ?></td>
                <td class="content"><?= $row[content] ?></td>
                <td class="viewCnt"><?= $row[viewCnt] ?></td>
                <td class="userName"><?= $row[userName] ?></td>
                <td class="createdDate"><?= $row[createdDate] ?></td>
            </tr>
        <?php endforeach;?>
    </table>
    <form action="BoardWrite.php">
        <input type="submit" value="글쓰기">
    </form>
</div>
</body>
</html>