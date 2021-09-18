let targetId;

$(document).ready(function () {
    // id 가 query 인 녀석 위에서 엔터를 누르면 execSearch() 함수를 실행하라는 뜻입니다.
    $('#query').on('keypress', function (e) {
        if (e.key == 'Enter') {
            execSearch();
        }
    });
    $('#close').on('click', function () {
        $('#container').removeClass('active');
    })

    $('.nav div.nav-see').on('click', function () {
        $('div.nav-see').addClass('active');
        $('div.nav-search').removeClass('active');

        $('#see-area').show();
        $('#search-area').hide();
    })
    $('.nav div.nav-search').on('click', function () {
        $('div.nav-see').removeClass('active');
        $('div.nav-search').addClass('active');

        $('#see-area').hide();
        $('#search-area').show();
    })

    $('#see-area').show();
    $('#search-area').hide();

    showProduct();
})


function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


function execSearch() {
    $('#search-result-box').empty()
    let query = $('#query').val()

    if (query == "") {
        alert('검색어를 입력해주세요.')
        $('#query').focus()
        return;
    }

    $.ajax({
        type: "GET",
        url: `/api/search?query=${query}`,
        success: function(res) {
            for (let i=0;i<res.length;i++) {
               let tmp_html = addHTML(res[i])
                $('#search-result-box').append(tmp_html)
            }
        }
    })
}

function addHTML(itemDto) {
    return `<div class="search-itemDto">
                <div class="search-itemDto-left">
                    <img src="${itemDto['image']}" alt="">
                </div>
                <div class="search-itemDto-center">
                    <div>${itemDto['title']}</div>
                    <div class="price">
                        ${numberWithCommas(itemDto['lprice'])}
                        <span class="unit">원</span>
                    </div>
                </div>
                <div class="search-itemDto-right">
                    <img src="images/icon-save.png" alt="" 
                         onclick='addProduct(${JSON.stringify(itemDto)})'>
                </div>
            </div>`
}


function addProduct(itemDto) {
    /**
     * modal 뜨게 하는 법: $('#container').addClass('active');
     * data를 ajax로 전달할 때는 두 가지가 매우 중요
     * 1. contentType: "application/json",
     * 2. data: JSON.stringify(itemDto),
     */
    // 1. POST /api/products 에 관심 상품 생성 요청
    // 2. 응답 함수에서 modal을 뜨게 하고, targetId 를 reponse.id 로 설정 (숙제로 myprice 설정하기 위함)
    $.ajax({
        type: "POST",
        data: JSON.stringify(itemDto),
        contentType: "application/json",
        url: "/api/products",
        success: function(res) {
            $('#container').addClass('active');
        }
    })
}

function showProduct() {
    $('#product-container').empty()
    $.ajax({
        type: "GET",
        url: "/api/products",
        success: function(res) {
            for (let i=0;i<res.length;i++) {
                let tmp_html = addProductItem(res[i])
                $('#product-container').append(tmp_html)
            }
        }
    })
}

function addProductItem(product) {
    let lprice = numberWithCommas(product['lprice'])
    return `<div class="product-card" onclick="window.location.href='${product['link']}'">
            <div class="card-header">
                <img src="${product['image']}"
                     alt="">
            </div>
            <div class="card-body">
                <div class="title">
                    ${product['title']}
                </div>
                <div class="lprice">
                    <span>${lprice}</span>원
                </div>
                <div class="isgood">
                    최저가
                </div>
            </div>
        </div>`;
}

function setMyprice() {
    /**
     * 숙제! myprice 값 설정하기.
     * 1. id가 myprice 인 input 태그에서 값을 가져온다.
     * 2. 만약 값을 입력하지 않았으면 alert를 띄우고 중단한다.
     * 3. PUT /api/product/${targetId} 에 data를 전달한다.
     *    주의) contentType: "application/json",
     *         data: JSON.stringify({myprice: myprice}),
     *         빠뜨리지 말 것!
     * 4. 모달을 종료한다. $('#container').removeClass('active');
     * 5, 성공적으로 등록되었음을 알리는 alert를 띄운다.
     * 6. 창을 새로고침한다. window.location.reload();
     */
}