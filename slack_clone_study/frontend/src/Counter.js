import React, {useState, useEffect} from "react";

// react는 클래스형/함수형으로 개발이 가능한데 스터디는 함수형으로 개발하고 hook을 사용함
const Counter = () => {
    // number 라는 state를 선언
    // setNumber 라는 함수를 선언한건데, 역할은 number 라는 state를 변경하는 함수
    const [number, setNumber] = useState(1);

    // number의 숫자가 변할때마다 아래 useEffect가 실행된다. (렌더링될때)
    useEffect(() => {
        console.log('rendering... : ');
    })
    
    // 자바스크립트의 함수
    const increase = () => {
        setNumber(number + 1);
    }

    const decrease = () => {
        setNumber(number - 1);
    }


    // 변수 return시 
    // () => increase 로도 사용됨
    return <>{number} 입니다.
        <button onClick={increase}>+</button>
        <button onClick={decrease}>-</button>
    </>;
}

export default Counter;