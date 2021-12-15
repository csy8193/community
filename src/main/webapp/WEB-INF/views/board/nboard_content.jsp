<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <div id="nboard-content">
        <h3>게시판 이름</h3>
        <h1 class="nboard-title">제목이 들어가는 자리입니다</h1>
        <div class="nboard-info">
                <img src="https://cdn.pixabay.com/photo/2014/07/31/22/50/photographer-407068_960_720.jpg" alt="">
                <div><a id="userId">UserId2323</a></div>
                <div>
                    <i class="far fa-heart"></i><span>2132</span>
                    <i class="far fa-comment-dots"></i><span>1222</span>
                    <i class="far fa-eye fa-2x"></i><span>3122</span>
                    <i class="fas fa-calendar-alt"></i><span>2021-12-08 01:01:01</span>
                </div>
        </div>
        <div class="nboard-inner">
            <img src="https://cdn.pixabay.com/photo/2021/09/27/03/19/bichon-6659330_960_720.jpg" alt="">
            <p>
                내용~~~~~~
            </p>
        </div>
        <div class="nboard-reply">
            <div>2개의 댓글</div>
            <div>
                <form action="">
                    <textarea type="text" name="replyText" id="reply-text"></textarea>
                    <button>등록</button>
                </form>
            </div>
            <ul class="all-reply">
                <li class="one-reply">
                    <img src="https://cdn.pixabay.com/photo/2021/09/27/03/19/bichon-6659330_960_720.jpg" alt="">
                    <div class="reply-user">
                        <span>id입니다</span><span>2021-12-11 17:50:01</span><button>신고하기</button>
                    </div>
                    <div class="reply-content">
                            댓글<br>
                            댓글<br>
                            댓글<br>
                            댓글<br>
                            댓글<br>
                            댓글<br>
                    </div>
                </li>
                <li class="one-reply feedback">
                    <img src="https://cdn.pixabay.com/photo/2016/12/30/17/27/cat-1941089_960_720.jpg" alt=""></img>
                    <div class="reply-user">
                        <i class="fas fa-level-up-alt"></i><span>id입니다</span><span>2021-12-11 17:50:01</span><button>신고하기</button>
                    </div>
                    <div class="reply-content">
                            댓글<br>
                    </div>
                </li>
                <li class="one-reply">
                    <img src="https://cdn.pixabay.com/photo/2016/11/21/14/47/kitten-1845789_960_720.jpg" alt="">
                    <div class="reply-user">
                        <span>id입니다</span><span>2021-12-11 17:50:01</span><button>신고하기</button>
                    </div>
                    <div class="reply-content">
                            댓글<br>
                            댓글<br>
                            댓글<br>
                            댓글<br>
                    </div>
                </li>
            </ul>
        </div>
    </div>