<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    
                    <c:forEach items="${products}" var="product" varStatus="counter">
                        <div class="templatemo_product_box">
                            <h1>${product.title}</h1>
                            <img src="resources/photos/${product.photo}" />
                            <div class="product_info">
                                <p id="author">Author:<br /><span id="name">${product.author}</span></p>
                                <p>
                                    Published: ${product.publishedYear}<br />
                                    Pages: ${product.pagesNo}<br />
                                    ISBN: ${product.isbn}
                                </p>
                                <h3>$${product.price}</h3>
                                <div class="buy_now_button"><a href="tocart/${product.productId}">Buy Now</a></div>
                            </div>

                        </div>
                                
                        <div class="${counter.count%2==0?"cleaner_with_height":"cleaner_with_width"}">&nbsp;</div>
                        
                    </c:forEach>
                        
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>