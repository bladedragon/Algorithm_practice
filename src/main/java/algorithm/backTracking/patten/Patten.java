package algorithm.backTracking.patten;

import javax.management.remote.rmi._RMIConnection_Stub;

public class Patten {
    //是否被匹配
    private boolean matched = false;
    //正则表达式
    private char[] patten;
    //正则表达式长度
    private int plen;

    public Patten(char[] patten,int plen){
        this.patten = patten;
        this.plen = plen;
    }

    /**
     * 匹配文本串的入口函数
     * @param text  被匹配的文本
     * @param tlen 文本长度
     * @return
     */
    public boolean match(char[] text,int tlen){
         matched = false;
        rmatch(0,0,text,tlen);
        return matched;

    }

    /**
     * 递归函数
     * @param ti 文本的推进点
     * @param pj  正则表达式的推进点
     * @param text
     * @param tlen
     */
    private void rmatch(int ti, int pj, char[] text, int tlen) {
        //匹配成功就不需要匹配了
        if(matched){
            return ;
        }
        //匹配到尽头
        if(pj == plen){
            if(ti == tlen){
                matched = true;
            }
            return;
        }

        if(patten[pj] == '*'){
            for(int k = 0; k < tlen-ti; k++){
                rmatch(ti+k,pj+1,text,tlen);
            }
        }else if(patten[pj] == '?'){
            rmatch(ti+1,pj+1,text,tlen);
            rmatch(ti,pj+1,text,tlen);

        }else if(patten[pj] == text[ti] && ti < tlen){
            rmatch(ti+1,pj+1,text,tlen);

        }
    }

}
