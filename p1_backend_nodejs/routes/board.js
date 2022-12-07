let express = require('express');
let router = express.Router();
let boardMapper = require("../models/boardMapper")
let release = require("../utils/release")

/** 글 등록 */
router.post('', async function(req, res){
    try {
        boardMapper.insertBoard(req.body.title, req.body.content, 1);

        release.send(res)
    } catch (e) {
        console.log("error: >>>>>>>>>> " + e)
        release.serverError(res)
    }
});

/** 리스트 조회 */
router.get('', async function(req, res){
    try {
        let results = await boardMapper.selectBoardList();

        release.send(res, results)
    } catch (e) {
        console.log(e + '>>>>> error')
        release.serverError(res)
    }``
});

/** 글 단건조회 */
router.get('/:boardIdx', async function(req, res){
    try {
        let result = await boardMapper.selectBoard(req.params.boardIdx)

        release.send(res, result)
    } catch (e) {
        console.log(e + '>>>>> error')
        release.serverError(res)
    }
});

/** 글 수정 */
router.put('/:idx', async function(req, res){
    try {
        await boardMapper.updateBoard(req.body.title, req.body.content, req.params.idx);
        let results = await boardMapper.selectBoardList();

        release.send(res, results)
    } catch (e) {
        console.log(e + '>>>>> error')
        release.serverError(res)
    }
});

/** 글 삭제 */
router.delete('/:idx', async function(req, res){
    try {
        await boardMapper.deleteBoard(req.params.idx);
        let results = await boardMapper.selectBoardList();

        release.send(res, results)
    } catch (e) {
        console.log(e + '>>>>> error')
        release.serverError(res)
    }
});

module.exports = router;
