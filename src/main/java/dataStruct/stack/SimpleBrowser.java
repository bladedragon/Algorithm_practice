package dataStruct.stack;

public class SimpleBrowser {

    private String currentPage;
    private LinkedBaseStack backStack;
    private LinkedBaseStack forwardStack;

    SimpleBrowser(){
        this.backStack = new LinkedBaseStack();
        this.forwardStack = new LinkedBaseStack();
    }

    //打开新页面，backStack 压入旧元素，同时清空forwardStack，因为已经无法到达
    public void open(String url){
        if(this.currentPage != null){
            this.backStack.push(this.currentPage);
            this.forwardStack.clear();
        }
        showUrl(url,"Open");
    }

    public boolean canBack(){
        return this.backStack.size()>0? true: false;
    }

    public boolean canForward(){
        return this.forwardStack.size()>0? true:false;
    }


    public String goBack(){
        if(canBack()){
            this.forwardStack.push(this.currentPage);

            String url = this.backStack.pop();
            showUrl(url,"Back");
            return url;
        }

        System.out.println("Cannot go back");
        return null;
    }

    public String goForward(){
        if(canForward()){
            this.backStack.push(this.currentPage);
            String url = this.forwardStack.pop();
            showUrl(url,"Forward");
            return url;
        }

        System.out.println("Cannot go forward");
        return null;
    }



    private void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix+"page == "+url);
        return ;
    }

}
