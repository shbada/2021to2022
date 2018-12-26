<?php
/**
 * Created by PhpStorm.
 * User: kimseohae
 * Date: 2018-12-17
 * Time: 15:58
 */

class Database {
    public $pdo;

    private $user = 'ims';
    private $password = 'Ims2018!';
    private $host = '54.180.77.161';
    private $db = 'test_ims';

    function __construct() {
        try{
            // MySQL PDO 객체 생성
            $this->pdo = new PDO("mysql:host=$this->host;dbname=$this->db", $this->user, $this->password);

            // 에러 출력
            $this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        }
        catch(Exception $e) {
            echo $e->getMessage();
        }
    }
}
?>
