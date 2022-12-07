import React, {useState} from "react";
import Potato from "./Potato";

const Counter2 = () => {
    let num = 0;
    const [num2, setNum2] = useState(0);

    const increase = () => {
        num++;
    }

    const increase2 = () => {
        setNum2(num2 + 1);
    }

    return (
        <div>
            <Potato mode={num}></Potato>
            <button onClick = {increase}>올려주세용</button>
            <Potato mode={num2}></Potato>
            <button onClick = {increase2}>올려주세용</button>
        </div>
        );
}

export default Counter2;