-- -----------------------------------------------------
-- Create procedure for calculating users count
-- -----------------------------------------------------

CREATE PROCEDURE getUserCount(OUT userCount INT)
BEGIN
    SELECT COUNT(*) INTO userCount FROM users;
END;