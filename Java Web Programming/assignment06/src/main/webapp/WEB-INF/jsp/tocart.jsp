<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    
                    <div class="templatemo_product_box">
                        <h1>${product.title}</h1>
                        <img src="resources/photos/${product.photo}" />
                        <div class="product_info">
                            <p id="author">Author:<br /><span id="name">${product.author}</span></p>
                            <p>In stock: ${product.stock}</p>
                            <h3>$${product.price}</h3>
                                <form method="post" action="addtocart">
                                    <input type="hidden" name="productId" value="${product.productId}" />
                                    <input id="textfield" type="number" min="1" max="${product.stock}" name="quantity" value="1" />
                                    <div class="buy_now_button"><input id="button" type="submit" value="Add to cart" /></div>
                                </form>
                        </div>

                    </div>
                       
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>