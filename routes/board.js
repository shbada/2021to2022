let express = require('express');
let router = express.Router();
let boardMapper = require("../models/boardMapper")

/** 글 등록 */
router.post('', function(req, res){
    let params = [req.body.title, req.body.content, 1];

    boardMapper.insertBoard(params, function(err, result) {
        if (err) return res.status(500).send("서버 에러가 발생하였습니다.");
        res.status(200).send('등록완료');
    })
});

/** 리스트 조회 */
router.get('', async function(req, res){
    try {
        let result = await boardMapper.selectBoardList();
        res.status(200).send(result);
    } catch (e) {
        console.log(e + '>>>>> error')
    }
});

/** 글 단건조회 */
router.get('/:boardIdx', async function(req, res){
    await boardMapper.selectBoard(req.params.boardIdx)
    res.status(200).send(result);
});

/** 글 수정 */
router.put('/:idx', function(req, res){
    let params = [req.body.title, req.body.content, req.params.idx];

    boardMapper.updateBoard(params, function (err, result) {
        res.status(200).send('수정완료');
    })
});

/** 글 삭제 */
router.delete('/:idx', function(req, res){
    boardMapper.deleteBoard(req.params.idx, function (err, result) {
        if (err) {
            console.log('failed... => ', err)
            return res.status(403).json({
                result : false,
                message: 'success'
            })
        } else {
            console.log('succeed... => ', result)
            let results = boardMapper.selectBoardList()
            return res.status(200).json({
                result : results,
                message: 'success'
            })
        }
    })
});

module.exports = router;
