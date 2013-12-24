Limitations :

 MessagePack/JSon : none (need annotations in DTO)
 ProtoStuff/Buff  :
   Map not working,
   No diff btween List/Array
   Enum must be unique in .prot file :  (oO)
      pack.prot:65:17: Note that enum values use C++ scoping rules, meaning that enum values are siblings of their type, not children of it.  Therefore, "NU
      LL" must be unique within "com.vidal.sandbox.statelessvxp.pojo.vidal.util.protbufgen.Pack", not just within "xxx".



 TODO:
  industrialise ".prot" generation
  add type of beans in command line
