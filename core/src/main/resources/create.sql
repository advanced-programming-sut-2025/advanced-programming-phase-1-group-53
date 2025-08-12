-- جدول کاربران
CREATE TABLE users
(
    user_id       SERIAL PRIMARY KEY,          -- شناسه کاربر (Auto Increment)
    username      VARCHAR(50) UNIQUE NOT NULL, -- نام کاربری
    email         VARCHAR(100)       NOT NULL, -- ایمیل
    password_hash TEXT               NOT NULL, -- رمز عبور هش‌شده
    created_at    TIMESTAMP DEFAULT NOW(),     -- تاریخ ثبت‌نام
    updated_at    TIMESTAMP DEFAULT NOW()      -- آخرین بروزرسانی پروفایل
);

-- جدول سشن‌ها
CREATE TABLE sessions
(
    session_id  UUID PRIMARY KEY,                                                -- شناسه سشن (UUID)
    user_id     INT       NOT NULL REFERENCES users (user_id) ON DELETE CASCADE, -- ارتباط با کاربر
    token       TEXT      NOT NULL,                                              -- توکن JWT یا مشابه
    login_time  TIMESTAMP DEFAULT NOW(),                                         -- زمان ورود
    last_active TIMESTAMP,                                                       -- آخرین فعالیت
    expires_at  TIMESTAMP NOT NULL,                                              -- زمان انقضای سشن
    ip_address  VARCHAR(45)                                                      -- آی‌پی کاربر (اختیاری)
);

-- برای تولید UUID در PostgreSQL
CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE users
    ADD COLUMN name     VARCHAR(100) UNIQUE,
    ADD COLUMN nickname VARCHAR(50),
    ADD COLUMN gender   VARCHAR(10),
    DROP COLUMN username;
