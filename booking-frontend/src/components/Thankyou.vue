<template>
    <div class="vue-tempalte">
        <form>
            <h3>Thanks for booking!</h3>
            <a href="/booking" style="text-decoration: none;">
                <button type="button" class="btn btn-dark btn-lg btn-block btn-margin">
                    Continue booking
                </button>
            </a>
            <a href="/booking-history" style="text-decoration: none;">
                <button type="button" class="btn btn-dark btn-lg btn-block btn-margin">
                    View booking history
                </button>
            </a>
            <a href="/" style="text-decoration: none;">
                <button type="button" class="btn btn-dark btn-lg btn-block btn-margin">
                    Back to main page
                </button>
            </a>
        </form>
    </div>
</template>

<script>
    export default {
        data() {
            return {}
        }
    }
</script>