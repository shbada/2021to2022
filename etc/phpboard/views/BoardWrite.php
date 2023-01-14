<?php
/**
 * Created by PhpStorm.
 * User: kimseohae
 * Date: 2018-12-17
 * Time: 16:48
 */
?>
<!doctype html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
<div id="board_write">
    <h1><a href="/">자유게시판</a></h1>
    <h4>글을 작성하는 공간입니다.</h4>
    <div id="write_area">
        <form action="BoardWriteSave.php" method="post">
            <div id="in_title">
                <textarea name="title" id="title" rows="1" cols="55" placeholder="제목" maxlength="100" required></textarea>
            </div>
            <div class="wi_line"></div>
            <div id="in_content">
                <textarea name="content" id="content" placeholder="내용" required></textarea>
            </div>
            <div class="bt_se">
                <button type="submit">글 작성</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>