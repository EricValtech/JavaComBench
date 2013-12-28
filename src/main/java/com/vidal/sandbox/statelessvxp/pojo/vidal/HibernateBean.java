package com.vidal.sandbox.statelessvxp.pojo.vidal;

/*import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
*/

import java.io.Serializable;
import java.util.*;
/*
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Function;
*/
/**
 * HibernateBean provides simple equals and hashcode replacement.
 *
 * @author Vidal Software
 */
public abstract class HibernateBean implements Serializable {
   
   
   
   private Integer id;

   protected HibernateBean() {
   }

   protected HibernateBean(Integer id) {
      this.id = id;
   }

   public/*final hibernate*/Integer getId() {
      return id;
   }

   public/*final hibernate*/void setId(Integer id) {
      this.id = id;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || !checkClass(getClass(), o.getClass()))
         return false;
      if (null == getId()) { // don't try to access id directly
         System.out.println("No id specified.");
         return false;
      } else {
         return (getId()==null)?false:getId().equals(((HibernateBean) o).getId()); // don't try to access id directly
      }
   }

   @SuppressWarnings("unchecked")
   private boolean checkClass(Class c1, Class c2) {
      return c1 == c2 || checkProxyClass(c1, c2);
   }

   @SuppressWarnings("unchecked")
   protected boolean checkProxyClass(Class c1, Class c2) {
      return false;
   }

   @Override
   public int hashCode() {
      return null == getId() ? super.hashCode() : getId();
   }

   @Override
   public String toString() {
      return null == getId() ? super.toString() : String.valueOf(getId());
   }

   public static Integer getId(HibernateBean bean) {
      return null == bean ? null : bean.getId();
   }

   public static List<Integer> getIds(Collection<? extends HibernateBean> beans) {
      Collection<? extends HibernateBean> safeList = (Collection<? extends HibernateBean>)firstNonNull(beans, new ArrayList<HibernateBean>());
      return new ArrayList(transform(safeList, new Transformer() {
    	  public Integer apply(HibernateBean bean) {
    	         return bean.getId();
    	      }		
      }));
   }


   private static Collection<Integer> transform(Collection<? extends HibernateBean> safeList,
		Transformer transformer) {
	   Collection<Integer> res = new ArrayList<Integer>();
	for(HibernateBean currBean: safeList) {
		res.add(transformer.apply(currBean));
	}
	return res;
}

private static Object firstNonNull(   Object first,  Object second) {
	if (first!=null) return first;
	if (second!=null) return second;
	throw new NullPointerException("Both objects can't be null");
}

public static Collection<Integer> getIds(Collection<? extends HibernateBean> beans, Collection<Integer> ids) {
      if (null == ids) {
         throw new NullPointerException("ids");
      }
      if (! isEmpty(beans)) {
         for (HibernateBean bean : beans) {
            if (null != bean) {
               ids.add(bean.getId());
            } else {
               System.out.println("Null object have no id.");
            }
         }
      }
      return ids;
   }

   public static Set<Integer> getIds(Integer id) {
      if (null == id) {
         return null;
      } else {
         return Collections.singleton(id);
      }
   }

   public static Set<Integer> getIds(HibernateBean bean) {
      return getIds(getId(bean));
   }

   public static Set<Integer> getUniqueIds(Collection<Integer> ids) {
      if (isEmpty(ids)) {
         return Collections.emptySet();
      } else {
         return new HashSet<Integer>(ids);
      }
   }

private static boolean isEmpty(Collection<?> coll) {
	return (coll==null)?true:(coll.size()==0);
}
}
