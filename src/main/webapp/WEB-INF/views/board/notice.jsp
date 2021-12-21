<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="${contextPath}/resources/css/notice.css">
</head>
<body>

	<jsp:include page="../common/header.jsp"></jsp:include>
	<main>
            <div id="main">
                <div class="main-header">
                    <a href="#"><h2>자주하는 질문</h2></a>
                    <h2> | </h2>
                    <a href="#"><h2>공지사항</h2></a>
                </div>
                <div class="main-content">
                    <table class="notice-table">
                        <tr>
                            <th class="table-hd table-head1">글번호</th>
                            <th class="table-hd table-head2">제목</th>
                            <th class="table-hd table-head3">작성일</th>
                        </tr>
                        <tr>
                            <td class="table-hd table-data1">3</td>
                            <td class="table-hd table-data2"><a href="#">공지 사항</a></td>
                            <td class="table-hd table-data3">21-12-11</td>
                        </tr>
                        <tr>
                            <td class="table-hd table-data1">2</td>
                            <td class="table-hd table-data2"><a href="#">공지 사항</a></td>
                            <td class="table-hd table-data3">21-12-11</td>
                        </tr>
                        <tr>
                            <td class="table-hd table-data1">1</td>
                            <td class="table-hd table-data2"><a href="#">공지 사항</a></td>
                            <td class="table-hd table-data3">21-12-11</td>
                        </tr>
                    </table>
                    <div class="page-number">
                        <ul class="page-ul">
                            <li>
                                <a href="#"><i class="fas fa-angle-double-left"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="fas fa-angle-left"></i></a>
                            </li>
                            <li>
                                <a href="#">1</a>
                            </li>
                            <li>
                                <a href="#">2</a>
                            </li>
                            <li>
                                <a href="#">3</a>
                            </li>
                            <li>
                                <a href="#">4</a>
                            </li>
                            <li>
                                <a href="#">5</a>
                            </li>
                            <li>
                                <a href="#"><i class="fas fa-angle-right"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="fas fa-angle-double-right"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
            
        </main>
</body>
</html>