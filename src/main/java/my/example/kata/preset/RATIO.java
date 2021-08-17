package my.example.kata.preset;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum RATIO {
    RATIO_16_9(16,9),
    RATIO_4_3(4,3),
    OTHERRATIOS(0,0);


    private int width;
    private int height;

    RATIO(int w, int h){

        this.width=w;
        this.height=h;

    }

    private static  final Comparator<RATIO> comparator=Comparator.comparing(RATIO::getWidth).thenComparing(RATIO::getHeight);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDefault(){

        return getHeight()*getWidth()==0;

    }


    public String toDisplayRatio(){

        return isDefault()? EncodingTaskHandlerUtils.DEFAULT_RATIO:getWidth()+ EncodingTaskHandlerUtils.PRESET_ENCODING_RATIO_SEPARATOR+getHeight();

    }

    public static List<RATIO> nonNullValues(){

      return   Arrays.stream(RATIO.values()).filter(r->r.getHeight()>0 && r.getWidth()>0).sorted(comparator.reversed()).collect(Collectors.toList());

    }

    @Override
    public String toString() {
        return getWidth()+":"+getHeight();
    }
}
