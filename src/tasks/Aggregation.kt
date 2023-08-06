package tasks

import contributors.User
import okhttp3.internal.userAgent

/*
TODO: Write aggregation code.

 In the initial list each user is present several times, once for each
 repository he or she contributed to.
 Merge duplications: each user should be present only once in the resulting list
 with the total value of contributions for all the repositories.
 Users should be sorted in a descending order by their contributions.

 The corresponding test can be found in test/tasks/AggregationKtTest.kt.
 You can use 'Navigate | Test' menu action (note the shortcut) to navigate to the test.
*/
fun List<User>.aggregate(): List<User> {
    val mutableListLogins = mutableListOf<String>()
    for (user in this) {
        if (!mutableListLogins.contains(user.login)) {
            mutableListLogins.add(user.login)
        }
    }
    val mutableListContributes = MutableList(mutableListLogins.size) { 0 }
    for (user in this) {
        val usrIndex = mutableListLogins.indexOf(user.login)
        mutableListContributes[usrIndex] += (user.contributions);
    }

    val mutableList = MutableList(mutableListLogins.size) { index ->
        User(
            mutableListLogins[index],
            mutableListContributes[index]
        )
    }
    mutableList.sortByDescending {
        it.contributions
    }

    return mutableList;
}
