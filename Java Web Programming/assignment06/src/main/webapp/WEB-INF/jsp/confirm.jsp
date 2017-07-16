<%@include file="top_and_left.jsp" %>

                <div id="templatemo_content_right">
                    
                    <div id="cart_info">
                        <p>Order confirmation:</p>
                        
                        <form method="post" action="confirm">
                            <p>
                                Enter your ID:<br /><br />
                                <input id="textfield" type="text" name="customerData" />
                            </p>
                            <div class="buy_now_button"><input id="button" type="submit" value="Confirm" /></div>
                        </form>
                    </div>
                       
                </div> <!-- end of content right -->

<%@include file="bottom.jsp" %>