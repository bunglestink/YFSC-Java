package tags.templates;

import java.util.Hashtable;
import java.util.Stack;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class GetTag extends BodyTagSupport {
	private String name;

	public void setName(String name) {
            this.name = name;
	}
	
        @Override
        public int doEndTag() throws JspException {
            Stack stack = (Stack)pageContext.getAttribute("template-stack", PageContext.REQUEST_SCOPE);

            if(stack == null)
                    throw new JspException("GetTag.doStartTag(): NO STACK");

            Hashtable params = (Hashtable)stack.peek();

            if(params == null)
                throw new JspException("GetTag.doStartTag(): NO HASHTABLE");

            PageParameter param = (PageParameter)params.get(name);

            if(param == null) {
                String body = getBodyContent().getString();
                try {
                    pageContext.getOut().print(body);
                }
                catch (Exception e) {
                    throw new JspException(e.getMessage());
                }
            }
            else {
                String content = param.getContent();

                if(param.isDirect()) {
                    try {
                        pageContext.getOut().print(content);
                    }
                    catch(java.io.IOException ex) {
                        throw new JspException(ex.getMessage());
                    }
                }
                else {
                    try {
                        pageContext.getOut().flush();
                        pageContext.include(content);
                    }
                    catch(Exception ex) { 
                        throw new JspException(ex.getMessage());
                    }
                }
            }
            return EVAL_PAGE;
	}
	
        public void release() {
            name = null;
	}
}
