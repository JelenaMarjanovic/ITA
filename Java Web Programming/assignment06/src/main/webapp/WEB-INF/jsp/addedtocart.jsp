<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    
                    <div id="cart_info">
                        <c:choose>
                            <c:when test="${quantity == 1}">
                                <p>Product "${product.title}" is successfully added to cart.</p>
                            </c:when>
                            <c:otherwise>
                                <p>Products "${product.title}" are successfully added to cart.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    
                    <div class="buy_now_button"><a href="cart">View cart</a></div>
                       
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>