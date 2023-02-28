package com.example.rudderstacks.common.util;

public class Constant {
    public enum ParamError {
        WRONG_INPUT_PARAMETER ("err","Input Parameters are either missing or invalid") ;
        private final String name;
        private final String desc;

        private ParamError(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }
    }
}
