<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/pic-detail.css">
	<title>사진 상세 게시판</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
    <div id="container">
        <div id="image-area">
            <div id="img-wrap">
                <img id="photo" src="https://web-wit.s3.ap-northeast-2.amazonaws.com/images/boardNews/3185/news1638888118_1.png">
            </div>
            <div id="img2-wrap">
                <div id="count">
                    <div id="count-area">
                        <ul class="ul-count">
                            <li class="img-count">
                                <a><img src="https://web-wit.s3.ap-northeast-2.amazonaws.com/images/boardNews/3185/news1638888118_1.png"></a>
                            </li>
                            <li class="img-count">
                                <a><img src="https://web-wit.s3.ap-northeast-2.amazonaws.com/images/boardNews/3185/news1638888118_2.png"></a>
                            </li>
                            <li class="img-count">
                                <a><img src="https://web-wit.s3.ap-northeast-2.amazonaws.com/images/boardNews/3185/news1638888119_3.png"></a>
                            </li>
                            <li class="img-count">
                                <a><img src="https://web-wit.s3.ap-northeast-2.amazonaws.com/images/boardNews/3185/news1638888119_4.png"></a>
                            </li>
                            <li class="img-count">
                                <a><img src="https://web-wit.s3.ap-northeast-2.amazonaws.com/images/boardNews/3185/news1638888119_5.png"></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div id="text-area">
            <div id="text-header">
                <div id="text-title">
                    <div class="user-img"></div>
                    <p>김집사</p>
                </div>
            </div>
            <div id="contend-area">
                <div id="contend">
                    <div style="word-break: break-all;">
                        저희집 반려묘 2마리 모두 엄청나게 사랑하는 내돈내산 필수템!
                        바로 역류방지쿠션입니다 :)

                        원래는 육아용품인데, 육아용품들이 고양이들한테 인기가 많다고 하더라고요 ?
                        입소문이 워낙 좋게 나서 동네 당근마켓에서 저렴하게 판매할때 얼른 사냥해 왔어요 !

                        두 아이 모두 너무 잘 쓰고,
                        서로 역방쿠 위에서 냥모나이트하려고 욕심부려서
                        결국 몇달 안되어 새로운 역방쿠를 영입했답니다 XD

                        로토토역방쿠 시어서커커버가 훨씬 털이 덜 붙더라고요.
                        제이앤제나역방쿠는 기본커버인데, 털이 어마어마합니다.

                        우리집 반려묘한테 쿠션을 사 주고 싶다- 싶은 집사님들께는
                        역류방지쿠션 핵추천입니다!! 乃
                    </div>
                    <div id="like-area">
                        <i class="far fa-heart"></i><span>좋아요 10개</span>
                        <span class="time">4시간 전</span>
                    </div>
                </div>
                <div class="text-comment">
                    <ul>
                        <li class="mine">
                            <div class="profile-img">
                                <div class="user-img2"></div>
                            </div>  
                            <div class="comment-wrapper">
                                <div class="profile"></div>
                                <div class="comment">
                                    <div class="comment-text">
                                        <pre id="reply-text"><strong class="userName">보리집사</strong>안녕하세요!! 너무 이뻐요</pre>
                                    </div>
                                    <div class="comment-item">
                                        <ul>
                                            <li>2021-12-11</li>
                                        </ul>
                                        <ul>
                                            <li>댓글달기</li>
                                        </ul>
                                    </div>
                                </div> 
                            </div>
                        </li>

                        <li class="mine">
                            <div class="profile-img">
                                <div class="user-img2"></div>
                            </div>  
                            <div class="comment-wrapper">
                                <div class="profile"></div>
                                <div class="comment">
                                    <div class="comment-text">
                                        <pre id="reply-text"><strong class="userName">보리집사</strong>안녕하세요!! 너무 이뻐요</pre>
                                    </div>
                                    <div class="comment-item">
                                        <ul>
                                            <li>2021-12-11</li>
                                        </ul>
                                        <ul>
                                            <li>댓글달기</li>
                                        </ul>
                                    </div>
                                </div> 
                            </div>
                        </li>

                        <li class="mine">
                            <div class="profile-img">
                                <div class="user-img2"></div>
                            </div>  
                            <div class="comment-wrapper">
                                <div class="profile"></div>
                                <div class="comment">
                                    <div class="comment-text">
                                        <pre id="reply-text"><strong class="userName">보리집사</strong>안녕하세요!! 너무 이뻐요</pre>
                                    </div>
                                    <div class="comment-item">
                                        <ul>
                                            <li>2021-12-11</li>
                                        </ul>
                                        <ul>
                                            <li>댓글달기</li>
                                        </ul>
                                    </div>
                                </div> 
                            </div>
                        </li>

                        <li class="mine">
                            <div class="profile-img">
                                <div class="user-img2"></div>
                            </div>  
                            <div class="comment-wrapper">
                                <div class="profile"></div>
                                <div class="comment">
                                    <div class="comment-text">
                                        <pre id="reply-text"><strong class="userName">보리집사</strong>안녕하세요!! 너무 이뻐요</pre>
                                    </div>
                                    <div class="comment-item">
                                        <ul>
                                            <li>2021-12-11</li>
                                        </ul>
                                        <ul>
                                            <li>댓글달기</li>
                                        </ul>
                                    </div>
                                </div> 
                            </div>
                        </li>

                        <li class="mine">
                            <div class="profile-img">
                                <div class="user-img2"></div>
                            </div>  
                            <div class="comment-wrapper">
                                <div class="profile"></div>
                                <div class="comment">
                                    <div class="comment-text">
                                        <pre id="reply-text"><strong class="userName">보리집사</strong>안녕하세요!! 너무 이뻐요</pre>
                                    </div>
                                    <div class="comment-item">
                                        <ul>
                                            <li>2021-12-11</li>
                                        </ul>
                                        <ul>
                                            <li>댓글달기</li>
                                        </ul>
                                    </div>
                                </div> 
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="write-area">
                <div id ="txt-box">
                    <textarea id="commentArea" style="resize: none;" name="reply_contents" cols="30" rows="10" placeholder="댓글을 작성하려면 위트에 로그인 해주세요."></textarea>
                </div>
                <div id="click_btn">
                    <button>등록</button>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>