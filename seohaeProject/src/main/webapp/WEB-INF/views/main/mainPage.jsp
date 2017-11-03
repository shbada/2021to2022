<%    
/**
 * 메인페이지
 * @author seohae
 * @since 2017. 11. 02.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  1. (2017. 11. 02 / seohae / 최초생성)
 *
 * </pre>
 */
 %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<jsp:include page="/mainTop.do" />
<!--feature section -->
<section id="feature" class="feature sections">
    <div class="container">
        <div class="row">
            <div class="main_feature text-center">

                <div class="col-sm-3">
                    <div class="single_feature">
                        <div class="single_feature_icon">
                            <i class="fa fa-clone"></i>
                        </div>

                        <h4>SLEEK DESIGN</h4>
                        <div class="separator3"></div>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting let. 
                            Lorem Ipsum has been the industry.</p>
                    </div>
                </div>

                <div class="col-sm-3">
                    <div class="single_feature">
                        <div class="single_feature_icon">
                            <i class="fa fa-heart-o"></i>
                        </div>

                        <h4>CLEAN CODE</h4>
                        <div class="separator3"></div>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting let. 
                            Lorem Ipsum has been the industry.</p>
                    </div>
                </div>

                <div class="col-sm-3">
                    <div class="single_feature">
                        <div class="single_feature_icon">
                            <i class="fa fa-lightbulb-o"></i>
                        </div>
                        <h4>CREATIVE IDEAS</h4>
                        <div class="separator3"></div>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting let. 
                            Lorem Ipsum has been the industry.</p>
                    </div>
                </div>

                <div class="col-sm-3">
                    <div class="single_feature">
                        <div class="single_feature_icon">
                            <i class="fa fa-comments-o"></i>
                        </div>

                        <h4>FREE SUPPORT</h4>
                        <div class="separator3"></div>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting let. 
                            Lorem Ipsum has been the industry.</p>
                    </div>
                </div>

            </div>
        </div>
    </div><!--End of container -->
</section><!--End of feature Section -->
<hr />


<!-- History section -->
<section id="history" class="history sections">
    <div class="container">
        <div class="row">
            <div class="main_history">
                <div class="col-sm-6">
                    <div class="single_history_img">
                        <img src="assets/images/stab1.png" alt="" />
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="single_history_content">
                        <div class="head_title">
                            <h2>OUR HISTORY</h2>
                        </div>
                        <p>It is a long established fact that a reader will be distracted by the readable content of a page 
                            when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal 
                            distribution of letters, as opposed to using 'Content here, content here', making it 
                            look like readable English. Many desktop publishing packages and web page editors now use 
                            Lorem Ipsum as their default model text, and a search for 'lorem ipsum' </p>

                        <a href="" class="btn btn-lg">BROWSE OUR WORK</a>
                    </div>
                </div>
            </div>
        </div><!--End of row -->
    </div><!--End of container -->
</section><!--End of history -->


<!-- service Section -->
<section id="service" class="service">
    <div class="container-fluid">
        <div class="row">
            <div class="main_service">
                <div class="col-md-6 col-sm-12 no-padding">

                    <div class="single_service single_service_text text-right">
                        <div class="head_title">
                            <h2>OUR SERVICES</h2>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-sm-10 col-xs-10 margin-bottom-60">
                                <div class="row">

                                    <div class="col-sm-10 col-sm-offset-1 col-xs-9 col-xs-offset-1">
                                        <article class="single_service_right_text">
                                            <h4>WEB DESIGN</h4>
                                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                                                Lorem Ip sum has been the industry's standard dummy text ever.</p>
                                        </article>
                                    </div>
                                    <div class="col-sm-1 col-xs-1">
                                        <figure class="single_service_icon">
                                            <i class="fa fa-heart"></i>
                                        </figure><!-- End of figure -->
                                    </div>
                                </div>
                            </div><!-- End of col-sm-12 -->

                            <div class="col-md-12 col-sm-10 col-xs-10 margin-bottom-60">
                                <div class="row">

                                    <div class="col-sm-10 col-sm-offset-1 col-xs-9 col-xs-offset-1">
                                        <article class="single_service_right_text">
                                            <h4>PRINT DESIGN</h4>
                                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                                                Lorem Ip sum has been the industry's standard dummy text ever.</p>
                                        </article>
                                    </div>
                                    <div class="col-sm-1 col-xs-1">
                                        <figure class="single_service_icon">
                                            <i class="fa fa-heart"></i>
                                        </figure><!-- End of figure -->
                                    </div>
                                </div>
                            </div><!-- End of col-sm-12 -->

                            <div class="col-md-12 col-sm-10 col-xs-10 margin-bottom-60">
                                <div class="row">

                                    <div class="col-sm-10 col-sm-offset-1 col-xs-9 col-xs-offset-1 margin-bottom-20">
                                        <article class="single_service_right_text">
                                            <h4>PHOTOGRAPHY</h4>
                                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                                                Lorem Ip sum has been the industry's standard dummy text ever.</p>
                                        </article>
                                    </div>
                                    <div class="col-sm-1 col-xs-1">
                                        <figure class="single_service_icon">
                                            <i class="fa fa-heart"></i>
                                        </figure><!-- End of figure -->
                                    </div>
                                </div>
                            </div><!-- End of col-sm-12 -->

                        </div>
                    </div>
                </div><!-- End of col-sm-6 -->

                <div class="col-md-6 col-sm-12 no-padding">
                    <figure class="single_service single_service_img">
                        <div class="overlay-img"></div>
                        <img src="assets/images/servicerightimg.jpg" alt="" />
                    </figure><!-- End of figure -->
                </div><!-- End of col-sm-6 -->

            </div>
        </div><!-- End of row -->
    </div><!-- End of Container-fluid -->
</section><!-- End of service Section -->


<section id="portfolio" class="portfolio sections">
    <div class="container-fluid">
        <div class="row">
            <div class="main_portfolio">
                <div class="col-sm-12">
                    <div class="head_title text-center">
                        <h2>RECENT WORKS</h2>
                        <div class="subtitle">
                            It has survived not only five centuries, but also the leap scrambled it to make a type.
                        </div>
                        <div class="separator"></div>
                    </div>
                </div>
                <div class="work_menu text-center">
                    <div id="filters" class="toolbar mb2 mt2">
                        <button class="btn-md fil-cat filter active" data-filter="all">ALL</button>/
                        <button class="btn-md fil-cat filter" data-rel="web" data-filter=".web">WEB DESIGN</button>/
                        <button class="btn-md fil-cat filter" data-rel="design" data-filter=".design">PRINT DESIGN</button>/
                        <button class="btn-md fil-cat filter" data-rel="flyers" data-filter=".flyers">ANIMATION</button>/
                        <button class="btn-md fil-cat filter" data-rel="bcards" data-filter=".bcards">ART</button>/
                        <button class="btn-md fil-cat filter" data-rel="photo" data-filter=".photo">PHOTOGRAPHY</button>/
                        <button class="btn-md fil-cat filter" data-rel="video" data-filter=".video">VIDEO</button>
                    </div> 

                </div>

                <div style="clear:both;"></div>     
                <div id="portfoliowork"> 
                    <div class="single_portfolio tile scale-anm web grid-item-width2 video" >
                        <img src="assets/images/pf1.jpg" alt="" />
                        <a href="assets/images/pf1.jpg" class="portfolio-img">
                            <div class="grid_item_overlay">
                                <div class="separator4"></div>
                                <h3>T-SHIRT DESIGN</h3>
                                <p>art / t-shirt</p>
                            </div>
                        </a>
                    </div>
                    <div class="single_portfolio tile scale-anm bcards photo" >
                        <img src="assets/images/pf2.jpg" alt="" />
                        <a href="assets/images/pf2.jpg" class="portfolio-img">
                            <div class="grid_item_overlay">
                                <div class="separator4"></div>
                                <h3>T-SHIRT DESIGN</h3>
                                <p>art / t-shirt</p>
                            </div>
                        </a>
                    </div>
                    <div class="single_portfolio tile scale-anm web video">
                        <img src="assets/images/pf3.jpg" alt="" />
                        <a href="assets/images/pf3.jpg" class="portfolio-img">
                            <div class="grid_item_overlay">
                                <div class="separator4"></div>
                                <h3>T-SHIRT DESIGN</h3>
                                <p>art / t-shirt</p>
                            </div>
                        </a>
                    </div>
                    <div class="single_portfolio tile scale-anm web photo" >
                        <img src="assets/images/pf4.jpg" alt="" />
                        <a href="assets/images/pf4.jpg" class="portfolio-img">
                            <div class="grid_item_overlay">
                                <div class="separator4"></div>
                                <h3>T-SHIRT DESIGN</h3>
                                <p>art / t-shirt</p>
                            </div>
                        </a>
                    </div>
                    <div class="single_portfolio tile scale-anm bcards design" >
                        <img src="assets/images/pf5.jpg" alt="" />  
                        <a href="assets/images/pf5.jpg" class="portfolio-img">
                            <div class="grid_item_overlay">
                                <div class="separator4"></div>
                                <h3>T-SHIRT DESIGN</h3>
                                <p>art / t-shirt</p>
                            </div>
                        </a>
                    </div>

                    <div class="single_portfolio tile scale-anm flyers video design">
                        <img src="assets/images/pf6.jpg" alt="" />
                        <a href="assets/images/pf6.jpg" class="portfolio-img">
                            <div class="grid_item_overlay">
                                <div class="separator4"></div>
                                <h3>T-SHIRT DESIGN</h3>
                                <p>art / t-shirt</p>
                            </div>
                        </a>
                    </div>
                    <div class="single_portfolio tile scale-anm photo flyers">
                        <img src="assets/images/pf7.jpg" alt="" />
                        <a href="assets/images/pf7.jpg" class="portfolio-img">
                            <div class="grid_item_overlay">
                                <div class="separator4"></div>
                                <h3>T-SHIRT DESIGN</h3>
                                <p>art / t-shirt</p>
                            </div>
                        </a>
                    </div>

                    <div class="single_portfolio tile scale-anm bcards video" >
                        <img src="assets/images/pf8.jpg" alt="" />
                        <a href="assets/images/pf8.jpg" class="portfolio-img">
                            <div class="grid_item_overlay">
                                <div class="separator4"></div>
                                <h3>T-SHIRT DESIGN</h3>
                                <p>art / t-shirt</p>
                            </div>
                        </a>
                    </div>

                </div>

                <div style="clear:both;"></div>  
            </div>
        </div>
    </div><!-- End off container --> 
</section> <!-- End off Work Section -->            



<!-- Study Section --> 
<section id="study" class="study text-center wow fadeIn"  data-wow-duration="2s" data-wow-dealy="1.5s">
    <div class="container">
        <div class="row">
            <div class="main_study_area sections">
                <div class="head_title text-center">
                    <h2>CASE STUDY</h2>
                    <div class="subtitle">
                        A brief story about how this process works, keep an eye till the end.
                    </div>
                    <div class="separator"></div>
                </div>
                <div class="single_study_content">
                    <div class="col-sm-6">
                        <div class="single_study_slid_area">

                            <div class="single_study_text">
                                <div class="study_slider"> 
                                    <div class="item">
                                        <div class="s_study_icon">
                                            <i class="fa fa-lightbulb-o"></i>  
                                        </div>
                                        <h4>aCCUMULATE CREATIVE IDEAS</h4>
                                        <div class="separator3"></div>
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting let. Lorem Ipsum has been the industry.
                                            Lorem Ipsum is simply dummy text of the printing and typesetting let.
                                            Lorem Ipsum has been the industry Printing and typelorem Ipsum has been the setting let.</p>

                                        <a href="" class="btn btn-lg">read more</a>
                                    </div>
                                    <div class="item">
                                        <div class="s_study_icon">
                                            <i class="fa fa-lightbulb-o"></i>  
                                        </div>
                                        <h4>aCCUMULATE CREATIVE IDEAS</h4>
                                        <div class="separator3"></div>
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting let. Lorem Ipsum has been the industry.
                                            Lorem Ipsum is simply dummy text of the printing and typesetting let.
                                            Lorem Ipsum has been the industry Printing and typelorem Ipsum has been the setting let.</p>

                                        <a href="" class="btn btn-lg">read more</a>
                                    </div>
                                    <div class="item">
                                        <div class="s_study_icon">
                                            <i class="fa fa-lightbulb-o"></i>  
                                        </div>
                                        <h4>aCCUMULATE CREATIVE IDEAS</h4>
                                        <div class="separator3"></div>
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting let. Lorem Ipsum has been the industry.
                                            Lorem Ipsum is simply dummy text of the printing and typesetting let.
                                            Lorem Ipsum has been the industry Printing and typelorem Ipsum has been the setting let.</p>

                                        <a href="" class="btn btn-lg">read more</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="single_study_right_img">
                        <div class="col-sm-6">
                            <div class="single_study_img">
                                <img src="assets/images/study.jpg" alt="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- End off row --> 
    </div><!-- End off Container --> 
</section><!-- End off Study Section --> 


<!-- Counter Section --> 
<section id="counter" class="counter">
    <div class="video_overlay">
        <div class="container">
            <div class="row">  
                <div class="col-sm-12">               
                    <div class="main_counter_area text-center">

                        <div class="row">
                            <div class="single_counter border_right">
                                <div class="col-sm-3 col-xs-12">
                                    <div class="single_counter_item">
                                        <i class="icon icon-thumbs-up"></i>
                                        <h2 class="statistic-counter">3891</h2>
                                        <h4 class="">User Favourites</h4>
                                    </div>
                                </div>
                            </div>

                            <div class="single_counter">
                                <div class="col-sm-3 col-xs-12">
                                    <div class="single_counter_item">
                                        <i class="icon icon-business-3"></i>
                                        <h2 class="statistic-counter">281</h2>
                                        <h4 class="">Posts Last 24 Hours</h4>  
                                    </div>
                                </div>
                            </div> 

                            <div class="single_counter">
                                <div class="col-sm-3 col-xs-12">
                                    <div class="single_counter_item">
                                        <i class="icon icon-people-32"></i>
                                        <h2 class="statistic-counter">618</h2>
                                        <h4 class="">Total Posts</h4>  
                                    </div>

                                </div>
                            </div>

                            <div class="single_counter">
                                <div class="col-sm-3 col-xs-12">
                                    <div class="single_counter_item">
                                        <i class="icon icon-cup"></i>
                                        <h2 class="statistic-counter">178</h2>
                                        <h4 class="">Amazing Features</h4>

                                    </div>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- End off container --> 
</section>   <!-- End of counter section -->

<!-- Pricing Section -->
<section id="pricing" class="pricing">
    <div class="container">
        <div class="row">
            <div class="main_pricing_area sections">
                <div class="head_title text-center">
                    <h2>OUR PRICING</h2>
                    <div class="subtitle">
                        A 30 days free trial for all. A brief story about how this process works, keep an eye till the end.
                    </div>
                    <div class="separator"></div>
                </div><!-- End off Head_title -->

                <div class="col-md-4 col-sm-6">
                    <div class="single_pricing">
                        <div class="pricing_head">
                            <h3>STARTER</h3>
                            <div class="pricing_price">
                                <div class="p_r text-center">$19</div>
                                <div class="m_t text-center">per month</div>
                            </div>
                        </div>

                        <div class="pricing_body">
                            <ul>
                                <li>Competition Analysis Methods</li>
                                <li>All Ranked URLs</li>
                                <li>International Support System</li>
                                <li>Social Media Tracking</li>
                            </ul>
                            <a href="" class="btn btn-md">CHOOSE PLAN</a>
                        </div>
                    </div>
                </div> <!-- End off col-md-4 -->
                <div class="col-md-4 col-sm-6">
                    <div class="single_pricing pricing_business">
                        <div class="pricing_head ">
                            <h3>PREMIUM</h3>
                            <div class="pricing_price">
                                <div class="p_r text-center">$39</div>
                                <div class="m_t text-center">per month</div>
                            </div>
                        </div>

                        <div class="pricing_body">
                            <ul>
                                <li>Competition Analysis Methods</li>
                                <li>All Ranked URLs</li>
                                <li>International Support System</li>
                                <li>Social Media Tracking</li>
                            </ul>
                            <a href="" class="btn btn-md">CHOOSE PLAN</a>
                        </div>
                    </div>
                </div> <!-- End off col-md-4 -->
                <div class="col-md-4 col-sm-6">
                    <div class="single_pricing">
                        <div class="pricing_head">
                            <h3>BUSINESS</h3>
                            <div class="pricing_price">
                                <div class="p_r text-center">$99</div>
                                <div class="m_t text-center">per month</div>
                            </div>
                        </div>

                        <div class="pricing_body">
                            <ul>
                                <li>Competition Analysis Methods</li>
                                <li>All Ranked URLs</li>
                                <li>International Support System</li>
                                <li>Social Media Tracking</li>
                            </ul>
                            <a href="" class="btn btn-md">CHOOSE PLAN</a>
                        </div>
                    </div>
                </div> <!-- End off col-md-4 -->
            </div>
        </div><!-- End off row -->
    </div><!-- End off container -->
</section><!-- End off Pricing Section -->

<!-- Team Section -->
<section id="team" class="team">
    <div class="main_team_area">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="head_title textwhite text-center margin-top-80">
                        <h2>OUR TEAM</h2>
                        <div class="subtitle">
                            Meet the craziest team. Share your thoughts with them.
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->

                    <div class="main_team">
                        <ul>
                            <li>
                                <div class="single_team_img">
                                    <img src="assets/images/team1.jpg" alt="" />
                                </div>
                                <div class="single_team_text">
                                    <h4>Semf Ucuk</h4>
                                    <p>Founder</p>
                                </div>
                            </li>
                            <li>
                                <div class="single_team_img">
                                    <img src="assets/images/team2.jpg" alt="" />
                                </div>
                                <div class="single_team_text">
                                    <h4>Kazi Erfan</h4>
                                    <p>Engineering</p>
                                </div>
                            </li>
                            <li>
                                <div class="single_team_img">
                                    <img src="assets/images/team3.jpg" alt="" />
                                </div>
                                <div class="single_team_text">
                                    <h4>Jeng Koli</h4>
                                    <p>Designer</p>
                                </div>
                            </li>
                            <li>
                                <div class="single_team_img">
                                    <img src="assets/images/team4.jpg" alt="" />
                                </div>
                                <div class="single_team_text">
                                    <h4>Pet Romak</h4>
                                    <p>Marketing</p>
                                </div>
                            </li>
                            <li>
                                <div class="single_team_img">
                                    <img src="assets/images/test2.jpg" alt="" />
                                </div>
                                <div class="single_team_text">
                                    <h4>Chet Pok</h4>
                                    <p>Web Developer</p>
                                </div>
                            </li>
                        </ul>
                    </div>

                </div><!-- End of main team contant --> 
            </div>
        </div><!-- End of container -->
    </div>
</section><!-- End off Team Section -->

<!-- Client Logo Section -->
<section id="clogo" class="clogo">
    <div class="container">
        <div class="row">
            <div class="main_clogo sections text-center">
                <div class="head_title text-center">
                    <h2>Great Integrations with Others</h2>
                    <div class="subtitle">
                        Suspendisse sed eros mollis, tincidunt felis eget, interdum erat. 
                        Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec. 
                    </div>
                    <div class="separator"></div>
                </div><!-- End off Head_title -->

                <div class="col-sm-3 col-xs-6"> 
                    <a href=""><img src="assets/images/clogo1.png" alt="" /></a>
                </div>
                <div class="col-sm-3 col-xs-6"> 
                    <a href=""><img src="assets/images/clogo2.png" alt="" /></a>
                </div>
                <div class="col-sm-3 col-xs-6"> 
                    <a href=""><img src="assets/images/clogo3.png" alt="" /></a>
                </div>
                <div class="col-sm-3 col-xs-6">
                    <a href=""><img src="assets/images/clogo4.png" alt="" /></a>
                </div>
                <div class="col-sm-3 col-xs-6"> 
                    <a href=""><img src="assets/images/clogo5.png" alt="" /></a>
                </div>
                <div class="col-sm-3 col-xs-6">
                    <a href=""><img src="assets/images/clogo6.png" alt="" /></a>
                </div>
                <div class="col-sm-3 col-xs-6"> 
                    <a href=""><img src="assets/images/clogo9.png" alt="" /></a>
                </div>
                <div class="col-sm-3 col-xs-6">
                    <a href=""><img src="assets/images/clogo8.png" alt="" /></a>
                </div>
            </div>
        </div>
    </div>
    <div class="divider"></div>
</section><!-- End off clogo Section -->


<!-- Blog Section -->
<section id="blog" class="blog">
    <div class="container-fluid">
        <div class="row">
            <div class="main_blog sections">
                <div class="head_title text-center">
                    <h2>OUR BLOG</h2>
                    <div class="subtitle">
                        Suspendisse sed eros mollis, tincidunt felis eget, interdum eratullam sit amet odio. 
                    </div>
                    <div class="separator"></div>
                </div><!-- End off Head_title -->

                <div class="main_blog_content">
                    <div class="col-sm-6">
                        <div class="single_blog_area textwhite">
                            <div class="row">
                                <div class="col-sm-6 no-padding">
                                    <div class="single_blog_img">
                                        <img src="assets/images/blog1.jpg" alt="" />
                                    </div>
                                </div>
                                <div class="col-sm-6 no-padding">
                                    <div class="single_blog_text s_b_left">
                                        <p>art/t-shirt</p>
                                        <h3>T-SHIRT DESIGN</h3>
                                        <div class="separator2"></div>
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the indu Stry's 
                                            standard dummy text ever since the 1500s, an unknown printer took a galley of type 
                                            a scrambled it to make a type specimen book.</p>

                                        <a href="" class="read_more">Read More >></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="single_blog_area textwhite">
                            <div class="row">
                                <div class="col-sm-6 no-padding">
                                    <div class="single_blog_img">
                                        <img src="assets/images/blog2.jpg" alt="" />
                                    </div>
                                </div>
                                <div class="col-sm-6 no-padding">
                                    <div class="single_blog_text s_b_left">
                                        <p>art/t-shirt</p>
                                        <h3>T-SHIRT DESIGN</h3>
                                        <div class="separator2"></div>
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the indu Stry's 
                                            standard dummy text ever since the 1500s, an unknown printer took a galley of type 
                                            a scrambled it to make a type specimen book.</p>

                                        <a href="" class="read_more">Read More >></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="single_blog_area textwhite">
                            <div class="row">

                                <div class="col-sm-6 col-sm-push-6 no-padding">
                                    <div class="single_blog_img">
                                        <img src="assets/images/blog3.jpg" alt="" />
                                    </div>
                                </div>

                                <div class="col-sm-6 col-sm-pull-6 no-padding">
                                    <div class="single_blog_text s_b_right">
                                        <p>art/t-shirt</p>
                                        <h3>T-SHIRT DESIGN</h3>
                                        <div class="separator2"></div>
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the indu Stry's 
                                            standard dummy text ever since the 1500s, an unknown printer took a galley of type 
                                            a scrambled it to make a type specimen book.</p>

                                        <a href="" class="read_more">Read More >></a>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="single_blog_area textwhite">
                            <div class="row">
                                <div class="col-sm-6 col-sm-push-6 no-padding">
                                    <div class="single_blog_img">
                                        <img src="assets/images/blog4.jpg" alt="" />
                                    </div>
                                </div>
                                <div class="col-sm-6 col-sm-pull-6 no-padding">
                                    <div class="single_blog_text s_b_right">
                                        <p>art/t-shirt</p>
                                        <h3>T-SHIRT DESIGN</h3>
                                        <div class="separator2"></div>
                                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the indu Stry's 
                                            standard dummy text ever since the 1500s, an unknown printer took a galley of type 
                                            a scrambled it to make a type specimen book.</p>

                                        <a href="" class="read_more">Read More >></a>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div><!-- End off container -->
</section><!-- End off Blog Section -->


<section id="contact" class="contact">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="contact_contant sections">
                    <div class="head_title text-center">
                        <h2>kEEP IN TOUCH</h2>
                        <div class="subtitle">
                            Nullam sit amet odio eu est aliquet euismod a a urna. Proin eu urna suscipit, dictum quam nec.  
                        </div>
                        <div class="separator"></div>
                    </div><!-- End off Head_title -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="main_contact_info">
                                <div class="row">
                                    <div class="contact_info_content padding-top-90 padding-bottom-60 p_l_r">
                                        <div class="col-sm-12">
                                            <div class="single_contact_info">
                                                <div class="single_info_text">
                                                    <h3>OUR ADDRESS</h3>
                                                    <h4>House #13, Streat road, Sydney 
                                                        2310 Australia</h4>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="single_contact_info">
                                                <div class="single_info_text">
                                                    <h3>CALL US</h3>
                                                    <h4>+ 880 168 109 1425</h4>
                                                    <h4>+ 0216809142</h4>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="single_contact_info">
                                                <div class="single_info_text">
                                                    <h3>EMAIL US</h3>
                                                    <h4>contactus@email.com</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 

                        <div class="col-sm-6">
                            <div class="single_contant_left padding-top-90 padding-bottom-90">
                                <form action="#" id="formid">
                                    <div class="col-lg-8 col-md-8 col-sm-10 col-lg-offset-2 col-md-offset-2 col-sm-offset-1">

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <input type="text" class="form-control" name="name" placeholder="First Name" required="">
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <input type="email" class="form-control" name="email" placeholder="Email" required="">
                                                </div>
                                            </div>
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <input type="text" class="form-control" name="subject" placeholder="Subject" required="">
                                                </div>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <textarea class="form-control" name="message" rows="7" placeholder="Message"></textarea>
                                        </div>

                                        <div class="">
                                            <input type="submit" value="SEND MESSAGE" class="btn btn-lg">
                                        </div>
                                    </div> 
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- End of contact section -->
<%@ include file="/WEB-INF/include/include-footer.jsp" %>