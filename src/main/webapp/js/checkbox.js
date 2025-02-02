/*
 * 전체 선택, 전체 해체, 부분 선택, 부분 해제를 위한 공통 체크박스
 * 전체 선택 체크박스 클래스 명 : select-all__checkbox
 * ex) <input type='checkbox' class='select-all__checkbox'/>
 * 부분 선택 체크박스 클래스 명 : selectCheckbox
 * ex) <input type='checkbox' class='select__checkbox'/>
 * 요청에 넘길 data 값은 default로 pk 값을 보내며 input의 value로 두어야한다.
 * ex) <input type='checkbox' class='select__checkbox' value='pk값'/>
 */

// 전체 선택 체크박스 요소
var selectAllCheckbox = document.querySelector("#allCheckBox");

/* 부분 선택 체크박스 요소들
 * 만약 체크 박스 라인이 더 이상 사용되지 않을 경우 해당 클래스를 classList 혹은 다른 방식으로라도 변경을 수동으로 해 주어야한다.
 */
var selectCheckboxList = document.querySelectorAll("#selectCheckbox");

// 체크박스 선택 시, 요청에 보낼 데이터 리스트
var datas = [];

// 체크된 체크박스 개수
var checkboxCount = 0;


/**
 * @function gfnSelectAllCheckbox : 전체 상품 선택 함수
 * @param  
 * @return 체크 박스 체크 여부 수행
*/
function gfnSelectAllCheckbox() {
	// 전체 선택 체크박스의 선택 여부
    var allCheckBoxchecked = selectAllCheckbox.checked;
    
    selectCheckboxList.forEach(checkbox => {
    	
        if (checkbox === null) {
            return;
        }
        
        // 전체 체크박스의 체크 여부에 따라서 부분 체크박스의 체크 상태 변경
        checkbox.checked = allCheckBoxchecked; 
        
        // 요청에 보낼 정보를 추출 TODO 유동적으로 바뀌는 부분
        var data = checkbox.value;

        // 전체 체크박스의 체크 여부에 따라서 데이터 저자 혹은 지우기
        allCheckBoxchecked ? datas.push(data) : (datas = []);
        // 중복 요소 제거
        datas = datas.filter((value, index) => datas.indexOf(value) === index);
        
    })
    
    // 전체 선택된 체크박스의 개수
    checkboxCount = allCheckBoxchecked ? selectCheckboxList.length : 0;
    
}

/**
 * @function gfnSelectProduct : 부분선택 체크박스 함수
 * @param  {Ojbect} : 체크된 체크 박스 요소
 * @return 체크 박스 체크 여부 수행
*/
function gfnSelectProduct(event) {
	
	// 클릭되어진 부분 선택 
    var checkbox = event.target;
    
    // 요청에 보낼 정보를 추출 TODO 유동적으로 바뀌는 부분
    var data = checkbox.value;
    
    // 체크박스의 체크 여부 상태에 따라서 로직 수행
    if (checkbox.checked) {
        datas.push(data); // 리스트에 담음
        checkboxCount++; // 동적 숫자 - 전체상품 개수 확인용
        
    } else {
        // unchecked 경우 제거
        datas = datas.filter((item) => item !== data);
        checkboxCount--; // 동적 숫자
        
    }

    // 체크박스 개수가 전체 체크 될 시, 전체 체크박스가 체크 된다.
    selectAllCheckbox.checked = (selectCheckboxList.length === checkboxCount);
}

/**
 * @function gfnRebindCheckbox : 동적으로 화면 변경 시, 다시 html dom과 연결
 * @param  
 * @return 동적으로 화면 변경 시, 다시 연결
*/
function gfnRebindCheckbox(){
	// 체크박스 선택 시, 요청에 보낼 데이터 리스트
	datas = [];
	// 체크된 체크박스 개수
	checkboxCount = 0;

	selectAllCheckbox = document.querySelector("#allCheckBox");
	selectCheckboxList = document.querySelectorAll("#selectCheckbox");
	selectAllCheckbox.checked = false;
	
	
	// 부분선택 체크박스 이벤트
	selectCheckboxList.forEach(selectCheckbox => {
		selectCheckbox.addEventListener("click", gfnSelectProduct);
	})
	
	// 사용자 편의 table rows
	tableRows = document.querySelectorAll('.table-row');
	tableRows.forEach(tableRow => {
		tableRow.addEventListener("click", fn_selectTableRowCheckbox);
	});
}

if (selectAllCheckbox !== null) {
	// 전체선택 체크박스 이벤트
	selectAllCheckbox.addEventListener("click", gfnSelectAllCheckbox);
	
}

if (selectCheckboxList !== null) {
	// 부분선택 체크박스 이벤트
	selectCheckboxList.forEach(selectCheckbox => {
		selectCheckbox.addEventListener("click", gfnSelectProduct);
		
	})
}


// 사용자 편의 메서드
var tableRows = document.querySelectorAll('.table-row');
tableRows.forEach(tableRow => {
	tableRow.addEventListener("click", fn_selectTableRowCheckbox);
});

/**
 * @function fn_selectTableRowCheckbox : 전체 상품 선택 함수
 * @param  
 * @return 체크 박스 체크 여부 수행
*/
function fn_selectTableRowCheckbox(event) {
	var tableCell = event.target;
	var isTableCell = tableCell.classList.contains('table-cell') && !tableCell.querySelector('#selectCheckbox');
	
	if (isTableCell) {
		var checkbox = tableCell.parentElement.querySelector('#selectCheckbox');
		var data = checkbox.value;
		
		// checkbox 체크드 방식을 변경하는 코드 작성
		checkbox.checked = !checkbox.checked;
		
		// 체크박스의 체크 여부 상태에 따라서 로직 수행
	    if (checkbox.checked) {
	        datas.push(data); // 리스트에 담음
	        checkboxCount++; // 동적 숫자 - 전체상품 개수 확인용
	        
	    } else {
	        // unchecked 경우 제거
	        datas = datas.filter((item) => item !== data);
	        checkboxCount--; // 동적 숫자
	        
	    }
	    
	    // 체크박스 개수가 전체 체크 될 시, 전체 체크박스가 체크 된다.
	    selectAllCheckbox.checked = (selectCheckboxList.length === checkboxCount);
	}
}


