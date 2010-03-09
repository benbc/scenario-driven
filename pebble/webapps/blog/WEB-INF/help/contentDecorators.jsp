<div class="contentItem">
  <h1>Content Decorators</h1>
  <h2>&nbsp;</h2>

  <div class="contentItemBody">
    <p>
      Content Decorators are a type of Pebble plugin that provides a way to change the way that
      blog entries, static pages, comments and TrackBacks are displayed in the HTML pages of your blog and the XML newsfeeds that are
      generated. The following decorators are included in the distribution, and those marked with a (*) are configured by default.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.RadeoxDecorator</b> (*)<br />
      Renders content inside blog entries and static pages between <code>&lt;wiki&gt;</code>...<code>&lt;/wiki&gt;</code> tags using the Radeox library.
      Internal style wiki links (e.g. <code>[aboutme]</code>) link to the static page with the same name. If the static page doesn't exist, the link lets you create the page.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.HtmlDecorator</b> (*)<br />
      Filters the HTML inside comments and TrackBacks to use only a limited, safe subset of HTML.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.EscapeMarkupDecorator</b> (*)<br />
      Escapes some XML/HTML markup between <code>&lt;escape&gt;</code>...<code>&lt;/escape&gt;</code> tags. This is useful where you
      would like to post large fragments of HTML, JSP, XML or Java code.
      In doing so, any &lt; or &gt; characters will be automatically escaped to &amp;lt; and &amp;gt; respectively.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.RelativeUriDecorator</b> (*)<br />
      Replaces relative URIs with absolute URLs so that they are rendered properly in browsers and newsreaders.
      For example, a link to <code>./images/someimage.jpg</code> would be transformed to <code>${blogUrl}images/someimage.jpg</code>.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.ReadMoreDecorator</b> (*)<br/>
      Adds a "Read more..." link to blog entries in the following cases.
    </p>
    <ul>
      <li>The blog entry excerpt is not empty and the page being displayed is a summary page (i.e. home, month or day
        page).
      </li>
      <li>The blog entry is aggregated (i.e. the original permalink has been specified).</li>
    </ul>

    <p>
      <b>net.sourceforge.pebble.decorator.BlogTagsDecorator</b> (*)<br/>
      Adds links from your blog entry back to your tags, for those tags associated with the blog entry.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.TechnoratiTagsDecorator</b><br/>
      Adds links from your blog entry back to tags on <a href="http://technorati.com/tags/">Technorati</a>, for those
      tags associated with the blog entry.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.SocialBookmarksDecorator</b><br/>
      Adds recommendation links to popular social bookmarking sites to your blog entry. 
      Currently included are: Slashdot, Digg, Reddit, Delicious and StumbleUpon
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.RelatedPostsDecorator</b><br/>
      Finds similar posts to the current within your posts. Similarity is determined by
      comparing the tags. Currently up to 6 similar posts are displayed.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.BlogCategoriesDecorator</b><br/>
      Adds links from your blog entry back to your categories, for those categories associated with the blog entry.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.NoFollowDecorator</b><br/>
      Provides support for the <a href="http://www.google.com/googleblog/2005/01/preventing-comment-spam.html">nofollow
      initiative</a> by modifying all links in comments and TrackBacks.
    </p>

    <p>
      <b>net.sourceforge.pebble.decorator.DisableResponseDecorator</b><br/>
      Disables comments and TrackBacks for the blog entry. This is useful if you are worried about spam when you don't
      have access to your blog. Needs to be used
      in conjunction with the DisableResponseListener (for comments and TrackBacks).
    </p>

    <a name="trackBackAutoDisovery"></a>

    <p>
      <b>net.sourceforge.pebble.decorator.TrackBackAutoDiscoveryDecorator</b><br/>
      Generates TrackBack Auto-Discovery links for blog entries. See the <a
        href="http://www.sixapart.com/pronet/docs/trackback_spec">TrackBack Technical Specification</a> for more
      details. To use this,
      you also need to configure the TrackBack Confirmation Strategy of your blog to be the <a
        href="./help/confirmationStrategies.html#noOpConfirmationStrategy">NoOpConfirmationStrategy</a>.
    </p>

    <a name="publishingExcerpts"></a>

    <p>
      <b>net.sourceforge.pebble.decorator.ExcerptDecorator</b><br/>
      Automatically creates an excerpt for blog entries if one doesn't already exist.
      Put this before the ReadMoreDecorator to ensure that "read more" links are also generated.
      <br /><br />
      The default maximum content length for truncation of the body is 255 characters, although this
      can be configured using the <code>ExcerptDecorator.maxLength</code> plugin property.
    </p>

    <h3>Configuring content decorators</h3>

    <p>
      To configure the decorators that your blog uses, modify the list of decorators on the <a
        href="viewPlugins.secureaction#contentDecorators">Plugins</a> page. Your blog is using the
      following blog listeners.
    </p>
    <pre class="codeSample"><c:forEach var="decorator" items="${blog.contentDecoratorChain.contentDecorators}">${decorator.class.name}<br /></c:forEach></pre>
    </p>
  </div>
</div>