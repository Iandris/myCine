<script type="text/javascript">
    initApp = function() {
        firebase.auth().onAuthStateChanged(function (user) {
            if (user) {
                var displayName = user.displayName;
                var email = user.email;
                var emailVerified = user.emailVerified;
                var photoURL = user.photoURL;
                var uid = user.uid;
                var providerDatea = user.providerData;

                user.getToken().then(function(accessToken) {
                    document.getElementById('sign-in').style = 'display:none';

                });
            } else {
                document.getElementById('sign-in').textContent = 'Sign-In';
            }
        }, function (error) {
            console.log(error);
        });
    };

    window.addEventListener('load', function() {
        initApp();
    });
</script>