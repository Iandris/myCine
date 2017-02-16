<script type="text/javascript">
    initApp = function() {
        firebase.auth().onAuthStateChanged(function (user) {
            if (user) {
                var displayName = user.displayName;
                var email = user.email;
                var emailVerified = user.emailVerified;
                var photoURL = user.photoURL;
                var uid = user.uid;
                var providerData = user.providerData;

                user.getToken().then(function(accessToken) {
                    document.getElementById('userinfo').textContent = JSON.stringify({
                        displayName: displayName,
                        email: email,
                        emailVerified: emailVerified,
                        photoURL: photoURL,
                        uid: uid,
                        accessToken: accessToken,
                        providerData: providerData
                    }, null, ' ');
                });
                window.user = user;
            } else {
                window.location = 'index.jsp';
            }
        }, function (error) {
            console.log(error);
        });
    };

    window.addEventListener('load', function() {
        initApp();
    });
</script>