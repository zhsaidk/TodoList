package zhsaidk.mapper.forUser;

public interface Mapper <F, T>{

    T map(F obj);

    T map(F from, T to);
}
