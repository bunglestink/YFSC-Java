package tags.templates;

import java.util.Hashtable;
import java.util.Stack;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PutTag extends BodyTagSupport {
    private String name, content;

    public void setName(String s) { name = s; }
    public void setContent(String s) {content = s; }

    @Override
    public int doAfterBody() throws JspException {
        InsertTag parent = (InsertTag)getAncestor("tags.templates.InsertTag");
        if(parent == null)
            throw new JspException("PutTag.doStartTag(): No InsertTag ancestor");

        Stack template_stack = parent.getStack();

        if(template_stack == null) 
            throw new JspException("PutTag: no template stack");	

        Hashtable params = (Hashtable)template_stack.peek();

        if(params == null) 
            throw new JspException("PutTag: no hashtable");	

        if (content != null) {
            params.put(name, new PageParameter(content, false));
        }
        else {
            String body = getBodyContent().getString();
            params.put(name, new PageParameter(body, true));
        }

        return SKIP_BODY;
    }
    
    @Override
    public void release() {
        name = content = null;
    }
    
    private BodyTagSupport getAncestor(String className) throws JspException {
        Class klass = null; // can't name variable "class"
        try {
                klass = Class.forName(className);
        }
        catch(ClassNotFoundException ex) {
                throw new JspException(ex.getMessage());
        }
        return (BodyTagSupport)findAncestorWithClass(this, klass);
    }
}
