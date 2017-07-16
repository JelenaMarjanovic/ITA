<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <base href="/assignment06/" />

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Book Store</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="icon" href="resources/images/favicon.ico">
        <link href="resources/templatemo_style.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div id="templatemo_container">

            <div id="templatemo_menu">
                <ul>
                    <li><a href="" class="current">Home</a></li>
                    <li><a href="admin/">Admin panel</a></li>
                    <li><a href="cart">View cart</a></li>
                </ul>
            </div> <!-- end of menu -->

            <div id="templatemo_header">
                <div id="board">
                    <p>Witcher<p>
                </div>
            </div> <!-- end of header -->

            <div id="templatemo_content">

                <div id="templatemo_content_left">
                    <div class="templatemo_content_left_section">
                        <h1>Categories</h1>
                        <ul>
                            <c:forEach items="${categories}" var="category">
                                <li><a href="./${category.categoryId}">${category.name}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div> <!-- end of content left -->