package tags.templates;

public class PageParameter { 
	private String content;
        private boolean direct;

	public void setContent(String s) {content = s; }
	public void setDirect(boolean s) { direct = s; }

	public String getContent() { return content;}
	public boolean isDirect() { return direct; }

	public PageParameter(String content, boolean direct) {
		this.content = content;
		this.direct = direct;
	}
}
