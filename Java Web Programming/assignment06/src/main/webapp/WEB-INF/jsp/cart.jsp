<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    
                    <div class="buy_now_button" ><a href="confirm">Confirm</a></div><br />
                    
                    <c:forEach items="${products}" var="product" varStatus="counter">
                    
                        <div class="templatemo_product_box">
                            <h1>${product.title}</h1>
                            <img src="resources/photos/${product.photo}" />
                            <div class="product_info">
                                <p id="author">Author:<br /><span id="name">${product.author}</span></p>
                                <p>Quantity: ${product.quantity}</p>
                                <h3>$${product.price}</h3>
                                    <form method="post" action="remove">
                                        <input type="hidden" name="productId" value="${product.productId}" />
                                        <div class="buy_now_button"><input id="button" type="submit" value="Remove" /></div>
                                    </form>
                            </div>                
                        </div>
                                        
                        <div class="${counter.count%2==0?"cleaner_with_height":"cleaner_with_width"}">&nbsp;</div>
                                        
                    </c:forEach>
                       
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>