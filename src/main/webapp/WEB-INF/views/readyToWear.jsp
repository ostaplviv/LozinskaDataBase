<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Olha Lozinska</title>

    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-118033320-3"></script>
    <script>
        window.dataLayer = window.dataLayer || [];

        function gtag() {
            dataLayer.push(arguments);
        }

        gtag('js', new Date());
        gtag('config', 'UA-118033320-3');
    </script>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <link href="/resources/css/index.css" rel="stylesheet">
    <script src="/resources/js/footer.js"></script>
</head>
<body>
<div class="text-center divLogo">
    <img src="/resources/img/olha-lozinska-logo-1.png" class="logoID">
</div>
<div class="header">
    <div class="headerItem"><a href="/"><img src="/resources/img/home.png" class="homePict"></a></div>
    <div class="headerItem"><a href="/readyToWear">READY TO WEAR</a></div>
    <div class="headerItem"><a href="/hauteCouture">HAUTE COUTURE</a></div>
    <div class="headerItem"><a href="/aboutUs">ABOUT US</a></div>
    <div class="headerItem"><a href="/contacts">CONTACTS</a></div>
</div>
<div class="container">
    <div class="row">
        <c:forEach var="showClothingModel" items="${showClothingModels.content}">
            <div class="col-lg-4 col-md-6 col-sm-12 text-center">
                <a href="/readyToWear/${showClothingModel.id}">
                    <img src="${showClothingModel.photoUrls[0]}?version=${showClothingModel.version}" class="item">
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<div class="footer">
    <div class="footerFirstRow">
        <div class="footerText">
            <a href="/aboutUs">© Olha Lozinska 2018</a>
        </div>
        <div class="footerFollow">
            Follow Us:
            <a href="https://www.instagram.com/olha.lozinska/" target="_blank">
                <img src="/resources/img/instagramLogo.png" class="footerLogo">
            </a>
            <a href="https://www.facebook.com/olha.lozinska.official/" target="_blank">
                <img src="/resources/img/facebookLogo.png" class="footerLogo">
            </a>
            <a href="https://in.pinterest.com/olhalozinska/" target="_blank">
                <img src="/resources/img/pinterestLogo.png" class="footerLogo">
            </a>
        </div>
    </div>
</div>
</body>
</html>