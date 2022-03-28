package pl.jreclaw.objectpool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point
{
    private double x;
    private double y;

    public void multipleBy(int mul) {
        x *= mul;
        y *= mul;
    }

    @Override
    public String toString(){
        return String.format("[%.4f,%.4f]",x,y);
    }
}
