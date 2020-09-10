# Reservation System

### Problem encounted and technical decision:  
- Problem: Cannot send Cross-Origin request to API
- Solved: Add CORS config in Spring Security  

- Problem: Both frontend and Admin Panel need to use JWT for authentication, but they use different User model and authentication policy
- Solved: Create 2 separate SecurityConfigurer in Spring Security; Add prefix in API URI to differentiate them for different security routes

- Problem: Cannot send DELETE request
- Solved: Setup CORS config in Spring Security to allow DELETE request
