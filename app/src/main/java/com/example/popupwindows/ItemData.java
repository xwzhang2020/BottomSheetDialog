package com.example.popupwindows;

public class ItemData {
        private String mTitle;
        private boolean mIsChecked;

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setChecked(boolean isChecked) {
            mIsChecked = isChecked;
        }

        public boolean isChecked() {
            return mIsChecked;
        }
}