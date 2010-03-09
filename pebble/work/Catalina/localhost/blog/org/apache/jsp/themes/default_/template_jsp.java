package org.apache.jsp.themes.default_;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class template_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(20);
    _jspx_dependants.add("/WEB-INF/fragments/prelude.jspf");
    _jspx_dependants.add("/WEB-INF/url.tld");
    _jspx_dependants.add("/WEB-INF/fragments/admin.jspf");
    _jspx_dependants.add("/WEB-INF/fragments/search.jspf");
    _jspx_dependants.add("/WEB-INF/fragments/coda.jspf");
    _jspx_dependants.add("/WEB-INF/tags/page.tag");
    _jspx_dependants.add("/WEB-INF/tags/linearNavigation.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/about.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/subscriptions.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/navigation.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/search.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/archivesByMonth.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/categories.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/tagCloud.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/recentBlogEntries.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/recentResponses.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/blogSummary.tag");
    _jspx_dependants.add("/WEB-INF/tags/sidebar/loginForm.tag");
    _jspx_dependants.add("/WEB-INF/tags/content.tag");
    _jspx_dependants.add("/WEB-INF/tags/poweredByPebble.tag");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fpebble_005fisNotAuthenticated;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fpebble_005fisAuthenticated;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fpebble_005fisNotAuthenticated = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fpebble_005fisAuthenticated = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fpebble_005fisNotAuthenticated.release();
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.release();
    _005fjspx_005ftagPool_005fpebble_005fisAuthenticated.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"error.action", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');
      if (_jspx_meth_template_005fpage_005f0(_jspx_page_context))
        return;
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_template_005fpage_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  template:page
    org.apache.jsp.tag.web.page_tag _jspx_th_template_005fpage_005f0 = new org.apache.jsp.tag.web.page_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_template_005fpage_005f0);
    _jspx_th_template_005fpage_005f0.setJspContext(_jspx_page_context);
    _jspx_th_template_005fpage_005f0.setJspBody(new Helper( 0, _jspx_page_context, _jspx_th_template_005fpage_005f0, null));
    _jspx_th_template_005fpage_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_template_005fpage_005f0);
    return false;
  }

  private boolean _jspx_meth_template_005flinearNavigation_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  template:linearNavigation
    org.apache.jsp.tag.web.linearNavigation_tag _jspx_th_template_005flinearNavigation_005f0 = new org.apache.jsp.tag.web.linearNavigation_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_template_005flinearNavigation_005f0);
    _jspx_th_template_005flinearNavigation_005f0.setJspContext(_jspx_page_context);
    _jspx_th_template_005flinearNavigation_005f0.setParent(_jspx_parent);
    _jspx_th_template_005flinearNavigation_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_template_005flinearNavigation_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005fabout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:about
    org.apache.jsp.tag.web.sidebar.about_tag _jspx_th_sidebar_005fabout_005f0 = new org.apache.jsp.tag.web.sidebar.about_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005fabout_005f0);
    _jspx_th_sidebar_005fabout_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005fabout_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005fabout_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005fabout_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005fsubscriptions_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:subscriptions
    org.apache.jsp.tag.web.sidebar.subscriptions_tag _jspx_th_sidebar_005fsubscriptions_005f0 = new org.apache.jsp.tag.web.sidebar.subscriptions_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005fsubscriptions_005f0);
    _jspx_th_sidebar_005fsubscriptions_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005fsubscriptions_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005fsubscriptions_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005fsubscriptions_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005fnavigation_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:navigation
    org.apache.jsp.tag.web.sidebar.navigation_tag _jspx_th_sidebar_005fnavigation_005f0 = new org.apache.jsp.tag.web.sidebar.navigation_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005fnavigation_005f0);
    _jspx_th_sidebar_005fnavigation_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005fnavigation_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005fnavigation_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005fnavigation_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005fsearch_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:search
    org.apache.jsp.tag.web.sidebar.search_tag _jspx_th_sidebar_005fsearch_005f0 = new org.apache.jsp.tag.web.sidebar.search_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005fsearch_005f0);
    _jspx_th_sidebar_005fsearch_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005fsearch_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005fsearch_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005fsearch_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005farchivesByMonth_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:archivesByMonth
    org.apache.jsp.tag.web.sidebar.archivesByMonth_tag _jspx_th_sidebar_005farchivesByMonth_005f0 = new org.apache.jsp.tag.web.sidebar.archivesByMonth_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005farchivesByMonth_005f0);
    _jspx_th_sidebar_005farchivesByMonth_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005farchivesByMonth_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005farchivesByMonth_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005farchivesByMonth_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005fcategories_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:categories
    org.apache.jsp.tag.web.sidebar.categories_tag _jspx_th_sidebar_005fcategories_005f0 = new org.apache.jsp.tag.web.sidebar.categories_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005fcategories_005f0);
    _jspx_th_sidebar_005fcategories_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005fcategories_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005fcategories_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005fcategories_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005ftagCloud_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:tagCloud
    org.apache.jsp.tag.web.sidebar.tagCloud_tag _jspx_th_sidebar_005ftagCloud_005f0 = new org.apache.jsp.tag.web.sidebar.tagCloud_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005ftagCloud_005f0);
    _jspx_th_sidebar_005ftagCloud_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005ftagCloud_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005ftagCloud_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005ftagCloud_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005frecentBlogEntries_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:recentBlogEntries
    org.apache.jsp.tag.web.sidebar.recentBlogEntries_tag _jspx_th_sidebar_005frecentBlogEntries_005f0 = new org.apache.jsp.tag.web.sidebar.recentBlogEntries_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005frecentBlogEntries_005f0);
    _jspx_th_sidebar_005frecentBlogEntries_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005frecentBlogEntries_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005frecentBlogEntries_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005frecentBlogEntries_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005frecentResponses_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:recentResponses
    org.apache.jsp.tag.web.sidebar.recentResponses_tag _jspx_th_sidebar_005frecentResponses_005f0 = new org.apache.jsp.tag.web.sidebar.recentResponses_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005frecentResponses_005f0);
    _jspx_th_sidebar_005frecentResponses_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005frecentResponses_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005frecentResponses_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005frecentResponses_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005fblogSummary_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:blogSummary
    org.apache.jsp.tag.web.sidebar.blogSummary_tag _jspx_th_sidebar_005fblogSummary_005f0 = new org.apache.jsp.tag.web.sidebar.blogSummary_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005fblogSummary_005f0);
    _jspx_th_sidebar_005fblogSummary_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005fblogSummary_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005fblogSummary_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005fblogSummary_005f0);
    return false;
  }

  private boolean _jspx_meth_sidebar_005floginForm_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  sidebar:loginForm
    org.apache.jsp.tag.web.sidebar.loginForm_tag _jspx_th_sidebar_005floginForm_005f0 = new org.apache.jsp.tag.web.sidebar.loginForm_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_sidebar_005floginForm_005f0);
    _jspx_th_sidebar_005floginForm_005f0.setJspContext(_jspx_page_context);
    _jspx_th_sidebar_005floginForm_005f0.setParent(_jspx_parent);
    _jspx_th_sidebar_005floginForm_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_sidebar_005floginForm_005f0);
    return false;
  }

  private boolean _jspx_meth_template_005fcontent_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  template:content
    org.apache.jsp.tag.web.content_tag _jspx_th_template_005fcontent_005f0 = new org.apache.jsp.tag.web.content_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_template_005fcontent_005f0);
    _jspx_th_template_005fcontent_005f0.setJspContext(_jspx_page_context);
    _jspx_th_template_005fcontent_005f0.setParent(_jspx_parent);
    _jspx_th_template_005fcontent_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_template_005fcontent_005f0);
    return false;
  }

  private boolean _jspx_meth_template_005fpoweredByPebble_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  template:poweredByPebble
    org.apache.jsp.tag.web.poweredByPebble_tag _jspx_th_template_005fpoweredByPebble_005f0 = new org.apache.jsp.tag.web.poweredByPebble_tag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_template_005fpoweredByPebble_005f0);
    _jspx_th_template_005fpoweredByPebble_005f0.setJspContext(_jspx_page_context);
    _jspx_th_template_005fpoweredByPebble_005f0.setParent(_jspx_parent);
    _jspx_th_template_005fpoweredByPebble_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_template_005fpoweredByPebble_005f0);
    return false;
  }

  private boolean _jspx_meth_pebble_005fisNotAuthenticated_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  pebble:isNotAuthenticated
    net.sourceforge.pebble.web.tagext.IsNotAuthenticatedTag _jspx_th_pebble_005fisNotAuthenticated_005f0 = (net.sourceforge.pebble.web.tagext.IsNotAuthenticatedTag) _005fjspx_005ftagPool_005fpebble_005fisNotAuthenticated.get(net.sourceforge.pebble.web.tagext.IsNotAuthenticatedTag.class);
    _jspx_th_pebble_005fisNotAuthenticated_005f0.setPageContext(_jspx_page_context);
    _jspx_th_pebble_005fisNotAuthenticated_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    int _jspx_eval_pebble_005fisNotAuthenticated_005f0 = _jspx_th_pebble_005fisNotAuthenticated_005f0.doStartTag();
    if (_jspx_eval_pebble_005fisNotAuthenticated_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("      |\n");
        out.write("      <a href=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pebbleContext.configuration.secureUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("login.secureaction\">");
        if (_jspx_meth_fmt_005fmessage_005f0(_jspx_th_pebble_005fisNotAuthenticated_005f0, _jspx_page_context))
          return true;
        out.write("</a>\n");
        out.write("      ");
        int evalDoAfterBody = _jspx_th_pebble_005fisNotAuthenticated_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_pebble_005fisNotAuthenticated_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fpebble_005fisNotAuthenticated.reuse(_jspx_th_pebble_005fisNotAuthenticated_005f0);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005fpebble_005fisNotAuthenticated.reuse(_jspx_th_pebble_005fisNotAuthenticated_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_pebble_005fisNotAuthenticated_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_pebble_005fisNotAuthenticated_005f0);
    // /themes/default/template.jsp(61,75) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f0.setKey("login.title");
    int _jspx_eval_fmt_005fmessage_005f0 = _jspx_th_fmt_005fmessage_005f0.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f0);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_pebble_005fisAuthenticated_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  pebble:isAuthenticated
    net.sourceforge.pebble.web.tagext.IsAuthenticatedTag _jspx_th_pebble_005fisAuthenticated_005f0 = (net.sourceforge.pebble.web.tagext.IsAuthenticatedTag) _005fjspx_005ftagPool_005fpebble_005fisAuthenticated.get(net.sourceforge.pebble.web.tagext.IsAuthenticatedTag.class);
    _jspx_th_pebble_005fisAuthenticated_005f0.setPageContext(_jspx_page_context);
    _jspx_th_pebble_005fisAuthenticated_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    int _jspx_eval_pebble_005fisAuthenticated_005f0 = _jspx_th_pebble_005fisAuthenticated_005f0.doStartTag();
    if (_jspx_eval_pebble_005fisAuthenticated_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("      |\n");
        out.write("      <a href=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pebbleContext.configuration.secureUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("logout.action?redirectUrl=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${blogUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\">Logout</a>\n");
        out.write("      ");
        int evalDoAfterBody = _jspx_th_pebble_005fisAuthenticated_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_pebble_005fisAuthenticated_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fpebble_005fisAuthenticated.reuse(_jspx_th_pebble_005fisAuthenticated_005f0);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005fpebble_005fisAuthenticated.reuse(_jspx_th_pebble_005fisAuthenticated_005f0);
    return false;
  }

  private class Helper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public Helper( int discriminator, JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public boolean invoke0( JspWriter out ) 
      throws Throwable
    {
      out.write("\n");
      out.write("\n");
      out.write("  <div id=\"body\">\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    <div id=\"header\">\n");
      out.write("      <div id=\"blogName\"><span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${blog.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span></div>\n");
      out.write("      <div id=\"blogDescription\"><span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${blog.description}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span></div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    ");
      out.write(" \n");
      out.write("    <div id=\"linearNavigation\">\n");
      out.write("      ");
      if (_jspx_meth_template_005flinearNavigation_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    <div id=\"sidebar\">\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005fabout_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005fsubscriptions_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005fnavigation_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005fsearch_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005farchivesByMonth_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005fcategories_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005ftagCloud_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005frecentBlogEntries_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005frecentResponses_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005fblogSummary_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_sidebar_005floginForm_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    <div id=\"content\">\n");
      out.write("      ");
      if (_jspx_meth_template_005fcontent_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    <div id=\"footer\">\n");
      out.write("      ");
      if (_jspx_meth_template_005fpoweredByPebble_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_pebble_005fisNotAuthenticated_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      ");
      if (_jspx_meth_pebble_005fisAuthenticated_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("  </div>\n");
      out.write("\n");
      return false;
    }
    public void invoke( java.io.Writer writer )
      throws JspException
    {
      JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        this.jspContext.getELContext().putContext(JspContext.class,this.jspContext);
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
        }
      }
      catch( Throwable e ) {
        if (e instanceof SkipPageException)
            throw (SkipPageException) e;
        throw new JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}
