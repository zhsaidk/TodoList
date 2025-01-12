package zhsaidk.mapper;

public interface Mapper <F, T>{

    T map(F obj);

    T map(F from, T to);
}
