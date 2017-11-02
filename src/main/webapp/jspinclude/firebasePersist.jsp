<script type="text/javascript">
    initApp = function() {
        firebase.auth().onAuthStateChanged(function (user) {
            if (user) {
                var displayName = user.displayName;
                var user_name = user.user_name;
                var emailVerified = user.emailVerified;
                var photoURL = user.photoURL;
                var uid = user.uid;
                var providerData = user.providerData;

                user.getToken().then(function(accessToken) {
                    document.getElementById('userinfo').textContent = JSON.stringify({
                        displayName: displayName,
                        user_name: user_name,
                        emailVerified: emailVerified,
                        photoURL: photoURL,
                        uid: uid,
                        accessToken: accessToken,
                        providerData: providerData
                    }, null, ' ');
                });
                window.user = user;
                document.getElementById('user_name').value = user_name;
                document.getElementById('uid').value = uid;
            } else {
                window.location = '../lander.jsp';
            }
        }, function (error) {
            console.log(error);
        });
    };

    window.addEventListener('load', function() {
        initApp();
    });
</script>