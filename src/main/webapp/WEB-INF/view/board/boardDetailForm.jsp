<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Petmily-Don't buy, Do Adopt</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/css/freeBoard.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/animate.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/magnific-popup.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/jquery.timepicker.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/flaticon.css">
    <link rel="stylesheet" href="/resources/petsitting-master/css/style.css">
</head>

<!-- 헤더 -->

<%@ include file="/WEB-INF/view/include/header.jspf" %>

<!-- 현재 페이지 -->

<section class="hero-wrap hero-wrap-2"
         style="background-image: url('/resources/petsitting-master/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
  <div class="overlay"></div>
  <div class="container">
    <div class="row no-gutters slider-text align-items-end">
      <div class="col-md-9 ftco-animate pb-5">
        <c:if test="${param.kindOfBoard eq '자유'}">
          <h1 class="mb-0 bread">자유 게시판 - 상세보기</h1>
        </c:if>
        <c:if test="${param.kindOfBoard eq '문의'}">
          <h1 class="mb-0 bread">문의 게시판 - 상세보기</h1>
        </c:if>
      </div>
    </div>
  </div>
</section>

<!-- content 상세보기 -->

<section class="ftco-section bg-light">
    <div class="container">

        <!-- content 내용 출력 -->

        <div class="card mb-2">
            <div class="card-body">
                <div class="media forum-item">

                    <!-- content title, name, wrTime, checkPublic -->

                    <div class="media-body ml-3">
                        <b> <span style="font-size: 2em;">${detailForm.title}</span> </b>
                        <h6 class="mt-1"></h6>
                        <small><a href="javascript:void(0)">${detailForm.name}</a></small>
                        <small><i class="far fa-comment ml-2"></i> date ${detailForm.wrTime} </small>

                        <c:if test="${param.kindOfBoard eq '문의'}">
                            <c:if test="${detailForm.checkPublic eq 'Y'}">
                                <span><small><i class="far fa-comment ml-2"></i> 공개</small></span>
                            </c:if>
                            <c:if test="${detailForm.checkPublic eq 'N'}">
                                <span><small><i class="far fa-comment ml-2"></i> 비공개</small></span>
                            </c:if>
                        </c:if>

                        <div class="modal-footer"></div>

                        <!-- content 내용 -->

                        <div class="mt-3 font-size-lg">${detailForm.content}</div>
                        <h1 class="mt-1"></h1>

                        <div class="modal-header"></div>
                        <div class="modal-footer">

                            <!-- content 수정, 삭제 -->

                            <c:if test="${authUser.getMNumber() == detailForm.getMNumber()}">
                                <button type="button" class="btn btn-light"
                                        onclick="location.href='/board/auth/modify?kindOfBoard=${param.kindOfBoard}&bNumber=${detailForm.getBNumber()}'">
                                    수정
                                </button>

                                <button type="button" class="btn btn-light"
                                        onclick="if(confirm('정말로 삭제하시겠습니까?'))
                                                {return location.href='/board/auth/delete?kindOfBoard=${param.kindOfBoard}&bNumber=${detailForm.getBNumber()}';}">
                                    삭제
                                </button>
                            </c:if>

                            <!-- content 목록 이동 버튼 -->

                            <span>
								<button type="button" class="btn btn-primary"
                                        onclick="location.href='/board/list?kindOfBoard=${param.kindOfBoard}'">글 목록</button>
							</span>

                            <!-- 댓글 작성하기 버튼 -->

                            <div class="media forum-item">
                                <button type="button" class="btn btn-primary" onclick="location.href='#message'">댓글 작성
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>

<!-- 풋터 -->

<%@ include file="/WEB-INF/view/include/footer.jspf" %>

<!-- loader -->

<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"/>
    </svg>
</div>

<script src="/resources/petsitting-master/js/jquery.min.js"></script>
<script src="/resources/petsitting-master/js/jquery-migrate-3.0.1.min.js"></script>
<script src="/resources/petsitting-master/js/popper.min.js"></script>
<script src="/resources/petsitting-master/js/bootstrap.min.js"></script>
<script src="/resources/petsitting-master/js/jquery.easing.1.3.js"></script>
<script src="/resources/petsitting-master/js/jquery.waypoints.min.js"></script>
<script src="/resources/petsitting-master/js/jquery.stellar.min.js"></script>
<script src="/resources/petsitting-master/js/jquery.animateNumber.min.js"></script>
<script src="/resources/petsitting-master/js/bootstrap-datepicker.js"></script>
<script src="/resources/petsitting-master/js/jquery.timepicker.min.js"></script>
<script src="/resources/petsitting-master/js/owl.carousel.min.js"></script>
<script src="/resources/petsitting-master/js/jquery.magnific-popup.min.js"></script>
<script src="/resources/petsitting-master/js/scrollax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="/resources/petsitting-master/js/google-map.js"></script>
<script src="/resources/petsitting-master/js/main.js"></script>

</body>
</html>