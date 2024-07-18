# NY Times- A comprehensive testing approach

[NY Times](https://www.nytimes.com/) is a popular News room website that focuses on delivering the latest global updates in the form of news-updates and user blogs.

The essential features of this website include
>1. News listings
>2. Searching by keywords
>3. Global as well as regional updates
>4. Subscription based delivery

## Website structure
At the top you can find the website header that includes a title, which is followed vertically by the popular filtering options like World, Business, Arts etc.
On the left-top there is a search option, and the right-top shows some other informative contexts.

At the bottom is the website footer that lists all the quick-links, followed by the website governance options like Contact Us, Accessibility etc.

## Testing approach
### Content-wise
Content wise, we will begin our testing with verifying if the header and footer contents are loaded. We will also verify some critical governance links to make sure the links are working. In the header we will validate if our default options are selected.

### Feature-wise
We will test the Search feature and validate if the search is loading the results for the keywords that are passed to it.
We will test if the Subscribe governance option is working.

*These tests will be run on Chrome as well as Firefox browsers.*

## Test cases in brief
Approach|Scenario|Test-method
:--------:|--------|-----------:
Content |Verify the date on the news paper|Tests.navigateToHomePageAndVerifyCurrentPage()
Content |Verify the regions on the news paper|Tests.verifyLoadedContent()
Content |Check if the **International** option is selected by default|Tests.navigateToHomePageAndVerifyCurrentPage()
Content |Verify if the governance links on the footer are loaded|Tests.verifyLoadedContent()
Feature |Validate the `Search` option|Tests.verifySearchOptionShowingRelatableResult()
Feature |Validate the `Subscribe` option|Tests.clickFooterNavigationLinks()
