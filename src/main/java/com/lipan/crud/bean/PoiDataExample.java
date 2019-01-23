package com.lipan.crud.bean;

import java.util.ArrayList;
import java.util.List;

public class PoiDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PoiDataExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPoiidIsNull() {
            addCriterion("poiId is null");
            return (Criteria) this;
        }

        public Criteria andPoiidIsNotNull() {
            addCriterion("poiId is not null");
            return (Criteria) this;
        }

        public Criteria andPoiidEqualTo(Integer value) {
            addCriterion("poiId =", value, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoiidNotEqualTo(Integer value) {
            addCriterion("poiId <>", value, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoiidGreaterThan(Integer value) {
            addCriterion("poiId >", value, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoiidGreaterThanOrEqualTo(Integer value) {
            addCriterion("poiId >=", value, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoiidLessThan(Integer value) {
            addCriterion("poiId <", value, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoiidLessThanOrEqualTo(Integer value) {
            addCriterion("poiId <=", value, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoiidIn(List<Integer> values) {
            addCriterion("poiId in", values, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoiidNotIn(List<Integer> values) {
            addCriterion("poiId not in", values, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoiidBetween(Integer value1, Integer value2) {
            addCriterion("poiId between", value1, value2, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoiidNotBetween(Integer value1, Integer value2) {
            addCriterion("poiId not between", value1, value2, "poiid");
            return (Criteria) this;
        }

        public Criteria andPoilngIsNull() {
            addCriterion("poiLng is null");
            return (Criteria) this;
        }

        public Criteria andPoilngIsNotNull() {
            addCriterion("poiLng is not null");
            return (Criteria) this;
        }

        public Criteria andPoilngEqualTo(String value) {
            addCriterion("poiLng =", value, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngNotEqualTo(String value) {
            addCriterion("poiLng <>", value, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngGreaterThan(String value) {
            addCriterion("poiLng >", value, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngGreaterThanOrEqualTo(String value) {
            addCriterion("poiLng >=", value, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngLessThan(String value) {
            addCriterion("poiLng <", value, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngLessThanOrEqualTo(String value) {
            addCriterion("poiLng <=", value, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngLike(String value) {
            addCriterion("poiLng like", value, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngNotLike(String value) {
            addCriterion("poiLng not like", value, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngIn(List<String> values) {
            addCriterion("poiLng in", values, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngNotIn(List<String> values) {
            addCriterion("poiLng not in", values, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngBetween(String value1, String value2) {
            addCriterion("poiLng between", value1, value2, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilngNotBetween(String value1, String value2) {
            addCriterion("poiLng not between", value1, value2, "poilng");
            return (Criteria) this;
        }

        public Criteria andPoilatIsNull() {
            addCriterion("poiLat is null");
            return (Criteria) this;
        }

        public Criteria andPoilatIsNotNull() {
            addCriterion("poiLat is not null");
            return (Criteria) this;
        }

        public Criteria andPoilatEqualTo(String value) {
            addCriterion("poiLat =", value, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatNotEqualTo(String value) {
            addCriterion("poiLat <>", value, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatGreaterThan(String value) {
            addCriterion("poiLat >", value, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatGreaterThanOrEqualTo(String value) {
            addCriterion("poiLat >=", value, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatLessThan(String value) {
            addCriterion("poiLat <", value, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatLessThanOrEqualTo(String value) {
            addCriterion("poiLat <=", value, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatLike(String value) {
            addCriterion("poiLat like", value, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatNotLike(String value) {
            addCriterion("poiLat not like", value, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatIn(List<String> values) {
            addCriterion("poiLat in", values, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatNotIn(List<String> values) {
            addCriterion("poiLat not in", values, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatBetween(String value1, String value2) {
            addCriterion("poiLat between", value1, value2, "poilat");
            return (Criteria) this;
        }

        public Criteria andPoilatNotBetween(String value1, String value2) {
            addCriterion("poiLat not between", value1, value2, "poilat");
            return (Criteria) this;
        }

        public Criteria andFileidIsNull() {
            addCriterion("fileId is null");
            return (Criteria) this;
        }

        public Criteria andFileidIsNotNull() {
            addCriterion("fileId is not null");
            return (Criteria) this;
        }

        public Criteria andFileidEqualTo(Integer value) {
            addCriterion("fileId =", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidNotEqualTo(Integer value) {
            addCriterion("fileId <>", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidGreaterThan(Integer value) {
            addCriterion("fileId >", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fileId >=", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidLessThan(Integer value) {
            addCriterion("fileId <", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidLessThanOrEqualTo(Integer value) {
            addCriterion("fileId <=", value, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidIn(List<Integer> values) {
            addCriterion("fileId in", values, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidNotIn(List<Integer> values) {
            addCriterion("fileId not in", values, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidBetween(Integer value1, Integer value2) {
            addCriterion("fileId between", value1, value2, "fileid");
            return (Criteria) this;
        }

        public Criteria andFileidNotBetween(Integer value1, Integer value2) {
            addCriterion("fileId not between", value1, value2, "fileid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}