/**
 * 
 */

 const formToSerialize = (formId) => JSON.stringify([].reduce.call(document.getElementById(formId), (data, element) => {
	   //이름이 있는 것을 대상으로함 
	    console.log(element);
	    if (element.name === '') return data; // name없는 거 무시
	    //radio와 checkbox인 경우는 반드시 선택된 것만 대상으로함 
	    if (element.type === 'radio' || element.type === 'checkbox') {
	        if (element.checked) {
				// 기존에 해당 name으로 데이터가 있지않다면, 문자열 하나 추가
	           if (typeof data[element.name] === 'undefined') {
	                data[element.name] = element.value;
	                
				// 이미 하나의 문자열이 들어있는 데, 또 추가 되야하는 경우	            
	           } else if(typeof data[element.name] === 'string') {
	              data[element.name] = [data[element.name], element.value]; // 2개
	              
	           // 2개 이상 들어오는 경우 push로 게속 추가
	           } else if(typeof data[element.name] === 'object') {
	              //배열에 문자열 값을 추가  
	              data[element.name].push(element.value);
	           }
	        }
	     } else {
	        //그외는 모두 대상으로 함 
	        data[element.name] = element.value;
	     }
	     return data;
	    },
	    {} //초기값 
	 )
);